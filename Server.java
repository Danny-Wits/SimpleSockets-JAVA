import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    Socket client;

    // !Constructors
    Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    void waitForConnection() {
        try {
            this.client = serverSocket.accept();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    boolean sendToClient(String message) {
        if (client == null)
            return false;
        try (var os = client.getOutputStream();
                var osw = new OutputStreamWriter(os);
                var bw = new BufferedWriter(osw);) {
            bw.write(message);
            bw.newLine();
            bw.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    String readFromClient() {
        if (client == null)
            return "No client";
        try (var is = client.getInputStream();
                var isr = new InputStreamReader(is);
                var br = new BufferedReader(isr);) {
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Error";
        }
    }
}