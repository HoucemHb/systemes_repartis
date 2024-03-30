package systemes_repartis.TP2.Ex2;

import java.io.*;
import java.net.*;

public class ServeurVoiture {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket socket = new DatagramSocket(1234);
        System.out.println("Serveur UDP démarré sur le port 1234.");

        byte[] receiveBuffer = new byte[4096];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveBuffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Voiture voiture = (Voiture) ois.readObject();
            System.out.println("Voiture reçue du client avec Carburant : "+voiture.getCarburant());
            // Modification de la voiture
            voiture.setCarburant(100); // Exemple de modification

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(voiture);
            byte[] sendBuffer = baos.toByteArray();

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
            socket.send(sendPacket);
        }
    }
}

