package systemes_repartis.TP1.Ex3;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        try{
            Personne personne = new Personne(21,"Houcem");
            output.writeObject(personne);
            Integer clientId =(Integer) input.readObject();
            System.out.println("Identificateur reçu du serveur: " + clientId);
        }
        catch(Exception e){

        }


        socket.close();
    }
}

