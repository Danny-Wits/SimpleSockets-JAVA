package simpleSockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The Client class represents a client that can connect to a server
 * using a socket, send messages, and receive responses.
 */
public class Client {
    public Socket me; // Socket to establish connection to the server
    private BufferedReader reader; // BufferedReader to read messages from the server
    private PrintWriter writer; // PrintWriter to send messages to the server

    /**
     * Constructor to create a client that connects to localhost on a specified
     * port.
     * 
     * @param port the port number on which the client attempts to connect to the
     *             server.
     */
    public Client(int port) {
        try {
            me = new Socket("localhost", port); // Initialize socket connection to localhost
            initReaderAndWriter(); // Initialize the I/O streams
        } catch (IOException e) {
            System.out.println("Connection Error");
            e.printStackTrace();
        }
    }

    /**
     * Constructor to create a client with an existing socket.
     * If the socket is null, it creates a default client connection on port 9999.
     * 
     * @param socket an existing Socket instance for the client connection.
     */
    public Client(Socket socket) {
        if (socket == null) {
            System.out.println("Null Error: Created Default Client");
            try {
                me = new Socket("localhost", 9999); // Default socket connection to localhost:9999
                initReaderAndWriter();
            } catch (IOException e) {
                System.out.println("Connection Error");
                e.printStackTrace();
            }
        } else {
            me = socket; // Use the provided socket
            try {
                initReaderAndWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Constructor to create a client that connects to a specified host and port.
     * 
     * @param host the server hostname to connect to.
     * @param port the port number on which to connect.
     */
    public Client(String host, int port) {
        try {
            me = new Socket(host, port); // Initialize socket connection to specified host and port
            initReaderAndWriter(); // Initialize the I/O streams
        } catch (IOException e) {
            System.out.println("Connection Error");
            e.printStackTrace();
        }
    }

    /**
     * Initializes the BufferedReader and PrintWriter for reading from and writing
     * to the server.
     * 
     * @throws IOException if there is an issue in initializing input/output
     *                     streams.
     */
    private void initReaderAndWriter() throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(me.getInputStream())); // Reader for incoming messages
        this.writer = new PrintWriter(me.getOutputStream(), true); // Writer for outgoing messages
        System.out.println("Connected to the server on port:" + me.getLocalPort());
    }

    /**
     * Reads a message from the server.
     * 
     * @return the message from the server as a String, or an error message if there
     *         is an issue.
     */
    public String readFromServer() {
        try {
            return reader.readLine(); // Reads a line from the server
        } catch (IOException e) {
            e.printStackTrace();
            return "Error Reading";
        }
    }

    /**
     * Sends a message to the server.
     * 
     * @param message the message to be sent to the server.
     * @return true if the message is successfully sent, false if there is an issue.
     */
    public boolean sendToServer(String message) {
        if (writer == null)
            return false;
        try {
            writer.println(message); // Sends the message to the server
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean close() {
        try {
            me.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
// ! @Danny-Wits: Documented by LLMs ...