package systemes_repartis.TP1.Ex3;



import java.io.*;
import java.net.*;

public class Serveur {
    private static int clientId = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket serveurSocket = new ServerSocket(9999);
        System.out.println("Le serveur est démarré sur le port 9999");

        while (true) {
            Socket socket = serveurSocket.accept();
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            try {
                Personne personne = (Personne) input.readObject();
                System.out.println("Reçu : " + personne.toString());
                output.writeObject(clientId++);
            } catch (Exception e) {
                System.err.println("Erreur serveur : " + e.getMessage());
                e.printStackTrace();
            }


        }
    }
}

