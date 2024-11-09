import simpleSockets.Client;

public class ClientRunner {
    public static void main(String[] args) {
        var client = new Client(9999);
        System.out.println(client.readFromServer());
        client.sendToServer("Danny-Wits is Here");

    }
}
