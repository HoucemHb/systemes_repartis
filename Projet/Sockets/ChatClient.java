package systemes_repartis.Projet.Sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5678;
        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket(hostname, port);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Enter your name: ");
            String userName = scanner.nextLine();
            writer.println(userName);

            // Thread to handle message sending
            Thread sendThread = new Thread(() -> {
                System.out.println("Welcome to the chat. Type 'bye' to exit");
                while (!socket.isClosed()) {
                    String message = scanner.nextLine();
                    writer.println(message);
                    if ("bye".equalsIgnoreCase(message)) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            // Thread to handle message reception
            Thread receiveThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });

            // Starting both threads
            sendThread.start();
            receiveThread.start();

            // Wait for the send thread to finish before closing resources
            sendThread.join();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Client terminated.");
        }
    }
}


