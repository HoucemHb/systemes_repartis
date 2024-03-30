package systemes_repartis.TP1.Ex2;

import java.io.*;
import java.net.*;

public class ClientVoiture {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234) ;
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            Voiture voiture = new Voiture(" Berline", "Mercedes-Benz Classe C");
            System.out.println("Le carburant avant la modification: "+voiture.getCarburant());
            output.writeObject(voiture);
            System.out.println("Voiture envoyée au serveur.");

            Voiture voitureModifiee = (Voiture) input.readObject();
            System.out.println("Le Carburant après la modification par le serveur " + voitureModifiee.getCarburant());

        } catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}

