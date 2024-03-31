package systemes_repartis.Projet.gRPC_Messagerie.src.main.java;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


import com.houcem.grpmessaging.Messaging.GetMessagesRequest;
import com.houcem.grpmessaging.Messaging.GetMessagesResponse;
import com.houcem.grpmessaging.Messaging.Message;
import com.houcem.grpmessaging.Messaging.SendMessageRequest;
import com.houcem.grpmessaging.Messaging.SendMessageResponse;
import com.houcem.grpmessaging.MessagingServiceGrpc.MessagingServiceImplBase;

public class GRPCServer {
    private static final List<Message> messagesList = new ArrayList<>();

    static class MessagingServiceImpl extends MessagingServiceImplBase {
        
        @Override
        public void sendMessage(SendMessageRequest request, StreamObserver<SendMessageResponse> responseObserver) {
            Message message = request.getMessage();
            synchronized (messagesList) {
                messagesList.add(message);
            }

            SendMessageResponse response = SendMessageResponse.newBuilder().setSuccess(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void getMessages(GetMessagesRequest request, StreamObserver<GetMessagesResponse> responseObserver) {
            List<Message> messagesForRecipient;
            synchronized (messagesList) {
                messagesForRecipient = messagesList.stream()
                        .filter(message -> message.getRecipient().equals(request.getUser()))
                        .collect(Collectors.toList());
            }

            GetMessagesResponse response = GetMessagesResponse.newBuilder()
                    .addAllMessages(messagesForRecipient)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

    
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(1234)
                .addService(new MessagingServiceImpl())
                .build();

        server.start();
        System.out.println("Server started on " + server.getPort());
        server.awaitTermination();
    }
}
