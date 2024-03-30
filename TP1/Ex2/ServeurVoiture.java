package systemes_repartis.TP1.Ex2;

import java.io.*;
import java.net.*;

public class ServeurVoiture {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur démarré sur le port 1234");
            Socket socket = serverSocket.accept();

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            Voiture voiture = (Voiture) input.readObject();
            System.out.println("Voiture reçue du client avec Carburant : "+voiture.getCarburant());

            // Modifier la quantité de carburant
            voiture.setCarburant(100); // Exemple de quantité

            output.writeObject(voiture);
            System.out.println("Voiture modifiée renvoyée au client avec carburant : "+voiture.getCarburant());

        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

