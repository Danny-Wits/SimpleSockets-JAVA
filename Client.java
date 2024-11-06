import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Client {
    Socket me;

    // !Constructors
    Client(int port) {
        try {
            me = new Socket("localhost", port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    Client(Socket s) {
        if (s == null) {
            System.out.println("Null Error : Created Default Client");
            try {
                me = new Socket("localhost", 9999);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        me = s;
    }

    Client(String host, int port) {
        try {
            me = new Socket(host, port);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    String readFromServer() {
        try (var is = me.getInputStream();
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
            return "Error Reading";
        }

    }

    boolean sendToServer(String message) {
        try (var os = me.getOutputStream();
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
}
