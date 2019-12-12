package myApp.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import myApp.config.ApplicationContext;
import myApp.dto.ResponseDto;
import myApp.protocol.Request;
import myApp.protocol.commands.*;
import myApp.protocol.loginLogout.LoginPayload;
import myApp.protocol.messages.MessagePayload;
import myApp.services.interfaces.*;
import myApp.servers.ClientHandler;

import java.sql.Connection;


public class RequestDispatcherImpl implements RequestDispatcher {
    private CommandService commandsService;
    private HelpService helpService;
    private MessagesService messagesService;
    private UsersService usersService;
    private ClientHandler clientHandler;
    private ApplicationContext applicationContext;

    public RequestDispatcherImpl(ApplicationContext applicationContext, ClientHandler clientHandler)
            throws IllegalAccessException, ClassNotFoundException {
        this.applicationContext = applicationContext;
        commandsService = applicationContext.getComponent(CommandService.class);
        helpService = applicationContext.getComponent(HelpService.class);
        messagesService = applicationContext.getComponent(MessagesService.class);
        usersService = applicationContext.getComponent(UsersService.class);
        this.clientHandler = clientHandler;
        Connection connection = applicationContext.getConnection();
        commandsService.getMessagesRepository().setConnection(connection);
        commandsService.getProductsRepository().setConnection(connection);
        messagesService.getMessagesRepository().setConnection(connection);
        commandsService.getUsersRepository().setConnection(connection);
        usersService.getUsersRepository().setConnection(connection);
    }

    public ResponseDto dispatch(Request request) {
        ResponseDto responseDto = null;
        switch (request.getHeader().get("typ")) {
            case "help": {
                responseDto = helpService.help();
                break;
            }
            case "login": {
                LoginPayload loginPayload = (LoginPayload) request.getPayload();
                responseDto = usersService.login(loginPayload.getEmail(), loginPayload.getPassword(), clientHandler);
                break;
            }
            case "logout": {
                usersService.logout(clientHandler);
                break;
            }
            case "message": {
                MessagePayload messagePayload = (MessagePayload) request.getPayload();
                responseDto = messagesService.message(request.getHeader().get("bearer"), messagePayload.getText());
                break;
            }
            case "command": {
                CommandPayload commandPayload = (CommandPayload) request.getPayload();
                DecodedJWT jwt = JWT.decode(request.getHeader().get("bearer"));
                switch (commandPayload.getCommand()) {
                    case "get messages": {
                        CommandListPayload commandListPayload = (CommandListPayload) request.getPayload();
                        responseDto = commandsService.commandGetMessages(commandListPayload.getSize(), commandListPayload.getPage());
                        break;
                    }
                    case "get products": {
                        CommandListPayload commandListPayload = (CommandListPayload) request.getPayload();
                        responseDto = commandsService.commandGetProducts(commandListPayload.getSize(), commandListPayload.getPage());
                        break;
                    }
                    case "add product": {
                        CommandAddProdPayload payload = (CommandAddProdPayload) request.getPayload();
                        if (jwt.getClaims().get("role").asString().equals("ADMIN")) {
                            responseDto = commandsService.addProduct(payload.getProductName(), payload.getPrice());
                        } else {
                            responseDto = commandsService.authorityFail();
                        }
                        break;

                    }
                    case "remove product": {
                        CommandRemoveProdPayload payload = (CommandRemoveProdPayload) request.getPayload();
                        if (jwt.getClaims().get("role").asString().equals("ADMIN")) {
                            responseDto = commandsService.removeProduct(payload.getProductName());
                        } else {
                            responseDto = commandsService.authorityFail();
                        }
                        break;
                    }
                    case "purchase": {
                        CommandPurchasePayload payload = (CommandPurchasePayload) request.getPayload();
                        responseDto = commandsService.purchase(payload.getProductName(), jwt.getClaims().get("sub").asInt());
                        break;
                    }
                }
            }
            break;
        }
        return responseDto;
    }
}
