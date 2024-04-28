import java.io.*;
import java.net.Socket;

public class SimpleClient {
    private Socket socket;
    private BufferedReader userInput;
    private PrintWriter out;
    private BufferedReader in;
    private int portNumber = 5445;
    //static boolean ready = true;

    public static void main(String[] args) {
        System.out.println("Simple Client Demo started....");
        //while (ready){
            new SimpleClient().startClient();

        //}
    }

    private void startClient() {
        try {
            socket = new Socket("localhost", portNumber);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userInput = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter your request (press Enter twice to send):");
            String line;
            StringBuilder requestBuilder = new StringBuilder();

            while ((line = userInput.readLine()) != null && !line.isEmpty()) {
                requestBuilder.append(line).append("\n");
                if (line.equals("exit")) {
                    System.out.println("Simple Client Demo finished!");
                    socket.close();
                   break;
                }

            }
            out.println(requestBuilder);
            String response = null;
            try {
                response = in.readLine();
            } catch (Exception e) {

            }
            if(response == null) {
                System.out.println("Connection closed by server");
                break;
            } else {
                System.out.println("Server response: " + response);
                if (response.equals("0 0")) {
                    System.out.println("Simple Client Demo finished!");
                    //socket.close();
                    break;
                }
            }

        }
        } catch (IOException e) {
            System.err.println("IOException during client operation: " + e.getMessage());
        }
    }
}
