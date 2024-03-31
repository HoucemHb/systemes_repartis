
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.houcem.grpmessaging.Messaging;
import com.houcem.grpmessaging.Messaging.GetMessagesRequest;
import com.houcem.grpmessaging.Messaging.GetMessagesResponse;
import com.houcem.grpmessaging.Messaging.SendMessageRequest;
import com.houcem.grpmessaging.Messaging.SendMessageResponse;
import com.houcem.grpmessaging.MessagingServiceGrpc;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MessagingClient {
    private final ManagedChannel channel;
    private final MessagingServiceGrpc.MessagingServiceBlockingStub blockingStub;

    public MessagingClient(String host, int port) {
        // Create a channel and a stub
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = MessagingServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void sendMessage(String sender,String recipient, String text) {
        Messaging.Message message = Messaging.Message.newBuilder()
                .setRecipient(recipient)
                .setSender(sender)
                .setText(text)
                .build();

        SendMessageRequest request = SendMessageRequest.newBuilder()
                .setMessage(message)
                .build();

       
        SendMessageResponse response = blockingStub.sendMessage(request);
        if (response.getSuccess()) {
            System.out.println("Message sent to " + recipient + " from " + sender + ": " + text);
        } else {
            System.out.println("Failed to send message to " + recipient);
        }
    }

    public void getMessages(String user) {
        GetMessagesRequest request = GetMessagesRequest.newBuilder()
                .setUser(user)
                .build();

      
        GetMessagesResponse response = blockingStub.getMessages(request);
        System.out.println("Messages for " + user + ":");
        for (Messaging.Message message : response.getMessagesList()) {
            System.out.println(message.getSender() + " says: " + message.getText());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        MessagingClient client = new MessagingClient("localhost", 1234);
        Scanner scanner = new Scanner(System.in);
        try {
        	// Send a message
        	  System.out.println("Enter your name:");
              String sender = scanner.nextLine();
              String action= "";
              do {
                  System.out.println("Choose the action (send/get) or enter exit to quit : ");
                  action = scanner.nextLine();
                  switch (action){
                      case "send":
                          System.out.println("Enter the recipient's name:");
                          String recipient = scanner.nextLine();
                          System.out.println("Enter the message:");
                          String message = scanner.nextLine();
                          client.sendMessage(sender,recipient, message);
                          break;
                      case "get":
                          System.out.println("Enter the name of the user you want to get the messages he recieved : ");
                          String user = scanner.nextLine();
                          client.getMessages(user);
                          break;
                      case "exit":
                          continue;
                      default:
                          System.out.println("Invalid Action");
                  }
              }while (!action.equals("exit"));

           
            
        

            

        } finally {
            // Shutdown the channel
            client.shutdown();
        }
    }
}
