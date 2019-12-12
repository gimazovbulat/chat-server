package myApp.servers;

import com.fasterxml.jackson.databind.ObjectMapper;
import myApp.config.ApplicationContext;
import lombok.NoArgsConstructor;
import myApp.dto.ResponseDto;
import myApp.dto.Type;
import myApp.protocol.Request;
import lombok.Getter;
import myApp.protocol.Response;
import myApp.security.TokenResolver;
import myApp.services.interfaces.RequestDispatcher;
import myApp.services.impl.RequestDispatcherImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Getter
@NoArgsConstructor
public class ClientHandler extends Thread {
    // связь с одним клиентом
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectMapper objectMapper;
    private ChatMultiServer chatMultiServer;
    private RequestDispatcher requestDispatcher;

    ClientHandler(
            Socket socket,
            ChatMultiServer chatMultiServer,
            ApplicationContext applicationContext) throws ClassNotFoundException, IllegalAccessException {
        this.clientSocket = socket;
        this.chatMultiServer = chatMultiServer;
        requestDispatcher = new RequestDispatcherImpl(applicationContext, this);
    }

    public void run() {
        try {
            objectMapper = new ObjectMapper();
            // получем входной поток для конкретного клиента
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                Request request = objectMapper.readValue(inputLine, Request.class);
                if (request.getHeader().get("typ").equals("help")
                        || request.getHeader().get("typ").equals("login")
                        || TokenResolver.verify(request.getHeader().get("bearer")) != null);
                ResponseDto responseDto = requestDispatcher.dispatch(request);
                String res = objectMapper.writeValueAsString(Response.build(responseDto));
                System.out.println(Response.build(responseDto).getClass());
                System.out.println(Response.build(responseDto).getData().getClass());
                System.out.println(responseDto);
                if (responseDto.getType().equals(Type.ALL)) {
                    for (ClientHandler client : chatMultiServer.getClients()) {
                        PrintWriter out = new PrintWriter(client.clientSocket.getOutputStream(), true);
                        out.println(res);
                    }
                } else out.println(res);

            }
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

