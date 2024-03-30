package systemes_repartis.TP2.Ex2;

import java.io.*;
import java.net.*;

public class ClientVoiture {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        Voiture voiture = new Voiture("Berline", "Mercedes");

        // Sérialisation de l'objet Voiture
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(voiture);
        byte[] sendBuffer = baos.toByteArray();

        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1234);
        socket.send(sendPacket);

        // Réception de l'objet Voiture modifié
        byte[] receiveBuffer = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        ByteArrayInputStream bais = new ByteArrayInputStream(receiveBuffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Voiture voitureModifiee = (Voiture) ois.readObject();

        System.out.println("Carburant après modification: " + voitureModifiee.getCarburant());

        socket.close();
    }
}

