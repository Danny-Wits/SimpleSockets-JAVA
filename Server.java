import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class represents a simple server using Java sockets.
 * It establishes a connection to a client, allows for message exchange,
 * and handles input/output streams for reading and writing messages.
 */
public class Server {
    public ServerSocket serverSocket; // ServerSocket that listens for incoming connections
    public Socket client; // Socket to handle communication with a connected client
    private BufferedReader reader; // BufferedReader for reading messages from the client
    private PrintWriter writer; // PrintWriter for sending messages to the client

    /**
     * Constructor to create a server that listens on a specific port.
     * 
     * @param port the port number on which the server listens for incoming
     *             connections.
     */
    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port); // Initialize the ServerSocket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor to create a server with an existing ServerSocket instance.
     * 
     * @param serverSocket the ServerSocket object to be used by the server.
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Waits for a client to connect and sets up the input and output streams.
     * The method blocks until a client connects, then assigns the `client`,
     * `reader`, and `writer` to facilitate communication.
     */
    public void waitForConnection() {
        try {
            System.out.println("Server waiting on : " + serverSocket.getLocalPort());
            this.client = serverSocket.accept(); // Accepts an incoming client connection
            this.reader = new BufferedReader(new InputStreamReader(client.getInputStream())); // Sets up the reader
            this.writer = new PrintWriter(client.getOutputStream(), true); // Sets up the writer
            System.out.println("Client Connected : " + client.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a message to the connected client.
     * 
     * @param message the message to be sent to the client.
     * @return true if the message is sent successfully; false if there is an issue
     *         (e.g., no client is connected or an error occurs).
     */
    public boolean sendToClient(String message) {
        if (client == null || writer == null) // Check if client is connected and writer is set up
            return false;
        try {
            writer.println(message); // Sends the message to the client
            writer.flush(); // Flushes the stream to ensure complete delivery
            System.out.println("Sent Message : " + client.isClosed());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reads a message from the connected client.
     * 
     * @return the message from the client as a String, or an error message if
     *         there is an issue (e.g., no client is connected or an error occurs).
     */
    public String readFromClient() {
        if (client == null || reader == null) // Check if client is connected and reader is set up
            return "No client or reader";
        try {
            return reader.readLine(); // Reads the message sent by the client
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
// ! @Danny-Wits: Documented AI ...