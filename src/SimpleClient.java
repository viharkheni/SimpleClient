
import java.io.*;
import java.net.Socket;

/* simple client demo */
/* single request scenario */

public class SimpleClient {
    Socket s = null;
    private int portNumber = 5445;
    private BufferedReader inStreamReader = null;
    private PrintWriter outStreamWriter = null;

    public static void main(String[] args) {
        System.out.println("Simple Client Demo started....");
        new SimpleClient().startClient();
        System.out.println("Simple Client Demo finished!");
    }

    private void startClient() {
        try {
            s = new Socket("localhost", portNumber);        // implicit connect --> blocking call
            // get the output (byte) stream from the data socket object and transform the byte stream into a
            // more comfortable  PrintWriter character stream
            outStreamWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            // send request to server
            outStreamWriter.println("Hello World");
            // get the input (byte) stream from the data socket object and transform the byte stream into a
            // more comfortable BufferedReader character stream
            inStreamReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // receive response
            String response = inStreamReader.readLine();  // blocking call
            System.out.println("Client received response from server: (" + response + ")");
            // close data socket to server
            s.close();
            System.out.println("Connection to server closed!");
        } catch (IOException e) {
            System.err.println("IOException during some socket operation..." + e.getLocalizedMessage());
        }
    }
}
