package myApp.servers;

import myApp.config.ApplicationContext;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.services.impl.RequestDispatcherImpl;
import myApp.services.interfaces.RequestDispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@NoArgsConstructor
public class ChatMultiServer {
    // список клиентов
    private List<ClientHandler> clients;
    private ApplicationContext applicationContext;
    private RequestDispatcher requestDispatcher;

    public ChatMultiServer(ApplicationContext applicationContext) throws ClassNotFoundException, IllegalAccessException {
        // Список для работы с многопоточностью
        clients = new CopyOnWriteArrayList<>();
        this.applicationContext = applicationContext;
        requestDispatcher = new RequestDispatcherImpl(applicationContext);
    }

    public void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        // запускаем бесконечный цикл
        while (true) {
            try {
                // запускаем обработчик сообщений для каждого подключаемого клиента
//                ClientHandler clientHandler = new ClientHandler(serverSocket.accept(), this, applicationContext);
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept(), this, applicationContext, requestDispatcher);
                clientHandler.start();

            } catch (IOException | ClassNotFoundException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}