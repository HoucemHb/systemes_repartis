package systemes_repartis.TP1.Ex1;
import java.io.*;
import java.net.*;
import java.util.Scanner;
class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        // Demander à l'utilisateur le nom et le port du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");

        try {
            port = keyb.nextInt();
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }
        // Tenter de se connecter au serveur et d'échanger des messages
        try {
            // Convertir le nom d'hôte en adresse IP
            InetAddress adr = InetAddress.getByName(host);
            // Etablir une connexion au serveur sur l'adresse et le port donnés
            Socket socket = new Socket(adr, port);
            // Initialiser les flux pour envoyer et recevoir des données
            ObjectOutputStream output =
                    new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input =
                    new ObjectInputStream(socket.getInputStream());
            // Envoyer un message au serveur
            output.writeObject(new String("ma première socket"));
            // Lire et afficher la réponse du serveur
            String chaine = (String) input.readObject();
            System.out.println(" recu du serveur : " + chaine);
        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
