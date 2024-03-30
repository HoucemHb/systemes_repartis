package systemes_repartis.TP2.Ex3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer {
    public static void main(String[] args) {
        // Le port sur lequel le serveur doit écouter.
        int port = 1250;

        try {
            // Création du socket UDP sur le port spécifié.
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Serveur UDP démarré sur le port " + port);

            // Buffer pour recevoir les données entrantes.
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // Création d'un paquet pour recevoir des données.
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                // Attendre la réception d'un paquet.
                socket.receive(receivePacket);
                String receivedString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Datagramme reçu: " + receivedString);



                // Récupération de l'adresse et du port de l'émetteur pour pouvoir lui répondre.
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Générer la date et l'heure courantes.
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                byte[] sendBuffer = currentTime.getBytes();

                // Création du paquet à envoyer, contenant la date et l'heure courantes.
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                // Envoyer le paquet de réponse à l'émetteur.
                socket.send(sendPacket);
                System.out.println("Date et heure courantes envoyées à l'émetteur.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exécution du serveur UDP : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
