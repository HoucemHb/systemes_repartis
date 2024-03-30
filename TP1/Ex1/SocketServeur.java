package systemes_repartis.TP1.Ex1;
import java.io.*;
import java.net.*;
import java.util.Scanner;


public class SocketServeur {
    public static void main(String argv[]) {
        int port=0;
        Scanner keyb = new Scanner(System.in);

        // Demander à l'utilisateur de saisir le port d'écoute du serveur
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (Exception e) {

            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java SocketServeur <port-serveur>");
            System.exit(-1);
        }

        try {
            // Créer un nouveau ServerSocket qui écoute sur le port spécifié
            ServerSocket serverSocket = new ServerSocket(port);

            // Accepter une connexion client
            Socket socket = serverSocket.accept(); // Le code se bloque ici en attendant la connexion d'un client

            // Initialiser les flux pour envoyer et recevoir des données
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lire un objet envoyé par le client et l'afficher et le convertir en chaine
            String chaine = (String) input.readObject();
            System.out.println("Reçu : " + chaine); // Afficher la chaîne reçue

            // Informer de l'adresse et du port du client
            System.out.println("Ça vient de : " + socket.getInetAddress() + ":" + socket.getPort());

            // Envoyer une réponse au client
            output.writeObject("Bien reçu");
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
