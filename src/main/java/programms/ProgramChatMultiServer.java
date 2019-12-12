package programms;


import myApp.apps.ServerApplication;

public class ProgramChatMultiServer {
    public static void main(String[] args) {
        ServerApplication serverApplication = new ServerApplication(args);
        serverApplication.startRunning();
    }
}