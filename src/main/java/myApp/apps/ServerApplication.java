package myApp.apps;

import com.beust.jcommander.JCommander;
import myApp.config.ApplicationContext;
import myApp.config.ApplicationContextImpl;
import myApp.config.ServerArgs;
import myApp.servers.ChatMultiServer;


public class ServerApplication {
    private String[] args;
    private ServerArgs argsServerClass;
    private ApplicationContext applicationContext;

    public ServerApplication(String[] args) {
        this.args = args;
        argsServerClass = new ServerArgs();
        JCommander.newBuilder()
                .addObject(argsServerClass)
                .args(args)
                .build();
    }

    public void startRunning() {
        try {
            startRunning0();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private void startRunning0() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        applicationContext = new ApplicationContextImpl();
        applicationContext.createConnection(argsServerClass.getProperties());
        applicationContext.createComponents();
        ChatMultiServer chatMultiServer = new ChatMultiServer(applicationContext);
        System.out.println(applicationContext.toString());
        chatMultiServer.start(argsServerClass.getPort());
    }
}
