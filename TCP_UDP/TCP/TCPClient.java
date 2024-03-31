package systemes_repartis.TCP_UDP.TCP;
import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8082);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String userInput;
        while ((userInput = reader.readLine()) != null) {
            out.println(userInput);
            System.out.println("Le serveur a répondu avec : " + in.readLine());
        }

        reader.close();
        out.close();
        in.close();
        socket.close();
    }
}

