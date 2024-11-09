import simpleSockets.Server;

public class ServerRunner {
    public static void main(String[] args) {
        System.out.println("Starting server ...");
        var server = new Server(9999);
        server.waitForConnection();
        server.sendToClient("Welcome to our Server");
        System.out.println(server.readFromClient());
        server.stop();
    }

}
