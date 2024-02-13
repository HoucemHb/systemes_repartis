package systemes_repartis;
import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8082);
        System.out.println("Serveur TCP démarré sur le port 8082.");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nouvelle connexion entrante : " + clientSocket);

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Réception d'un message du client : " + inputLine);
                out.println("Reçu : " + inputLine);
            }

            in.close();
            out.close();
            clientSocket.close();
        }
    }
}

