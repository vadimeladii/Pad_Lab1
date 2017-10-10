package md.utm.fcim.broker.connection;

import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.enums.UserType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler {

    private List<ClientConnection> receiverClientsConnection = new ArrayList<>();
    private List<ClientConnection> clientConnections = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public void add(ClientConnection clientConnection) {
        while (true) {
            try {
                Message message = (Message) clientConnection.getObjectInputStream().readObject();
                switch (message.getMessageStatus()) {
                    case INIT:
                        System.out.println("Request Handler INIT");
                        clientConnections.add(clientConnection);
                        if(message.getUserType().equals(UserType.RECEIVER))
                            receiverClientsConnection.add(clientConnection);
                        System.out.println("Number of allClients connected: " + clientConnections.size());
                        System.out.println(message);
                        break;
                    case DATA:
                        System.out.println("Request Handler DATA");
                        messages.add(message);
                        dispatch(message);
                        System.out.println(message);
                        break;
                    case CLOSE:
                        System.out.println("Request Handler CLOSE");
                        messages.forEach(System.out::println);
                        disconnect(clientConnection);
                        return;
                    default:
                        System.out.println("Request Handler default");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(Message message) {
        receiverClientsConnection
                .forEach(clientConnection -> clientConnection.sendToClient(message));
    }

    private void disconnect(ClientConnection clientConnection) {
        clientConnection.closeConnection();
        clientConnections.remove(clientConnection);
        System.out.println("Number of allClients connected: " + this.clientConnections.size());
    }
}
