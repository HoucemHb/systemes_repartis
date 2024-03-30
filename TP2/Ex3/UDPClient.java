package systemes_repartis.TP2.Ex3;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        // The server's hostname or IP address
        String serverAddress = "localhost"; // Use the server's IP address if it's running on a different machine
        // The port number must match the one used by the server
        int serverPort = 1250;

        try {
            // Create a socket to send and receive data
            DatagramSocket socket = new DatagramSocket();

            // Convert the string message into bytes
            byte[] sendData = "Requesting current date and time".getBytes();

            // Create a packet to send data to the server
            InetAddress address = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);

            // Send the packet to the server
            socket.send(sendPacket);

            // Prepare a packet to receive the server's response
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Wait for the response from the server
            socket.receive(receivePacket);

            // Convert the response to a string and print it
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server response: " + response);

            // Close the socket
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}

