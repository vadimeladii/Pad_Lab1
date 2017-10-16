package md.utm.fcim.broker.connection;

import md.utm.fcim.broker.channel.ChannelService;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.enums.UserType;
import md.utm.fcim.common.repository.MessageRepository;
import md.utm.fcim.common.repository.impl.MessageRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestHandler {

    private List<ClientConnection> receiverClientsConnection = new ArrayList<>();
    private List<ClientConnection> clientConnections = new ArrayList<>();
    private List<ChannelService> channelServices = new ArrayList<>();
    private ConcurrentLinkedQueue<Message> messages = new ConcurrentLinkedQueue<>();
    private MessageRepository messageRepository = new MessageRepositoryImpl();

    public RequestHandler() {
        messages = messageRepository.findAll();
        this.saveMessages();
    }

    public void add(ClientConnection clientConnection) {
        while (true) {
            try {
                Message message = (Message) clientConnection.getObjectInputStream().readObject();
                switch (message.getMessageStatus()) {
                    case INIT:
                        this.handleMessageWithTypeInit(clientConnection, message);
                        break;
                    case SINGLE:
                        this.handleMessageWithTypeSingle(clientConnection, message);
                        break;
                    case CHANNEL:
                        this.handleMessageWithTypeChannel(clientConnection, message);
                        break;
                    case CLOSE:
                        this.handleMessageWithTypeClose(clientConnection, message);
                        return;
                    default:
                        System.out.println("Request Handler default");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveMessages() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                    messageRepository.save(messages);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void dispatch(Message message) {
        receiverClientsConnection
                .forEach(clientConnection -> {
                    clientConnection.sendToClient(message);
                    messages.remove(message);
                });
    }

    private void disconnect(ClientConnection clientConnection) {
        clientConnection.closeConnection();
        clientConnections.remove(clientConnection);
        System.out.println("Number of allClients connected: " + this.clientConnections.size());
    }


    private void handleMessageWithTypeInit(ClientConnection clientConnection, Message message) {
        System.out.println("Request Handler INIT");
        clientConnections.add(clientConnection);
        if (message.getUserType().equals(UserType.RECEIVER)) {
            receiverClientsConnection.add(clientConnection);
            messages.forEach(this::dispatch);
        }
        System.out.println("Number of allClients connected: " + clientConnections.size());
        System.out.println("----------------------------------------------------------------------------");
    }

    private void handleMessageWithTypeSingle(ClientConnection clientConnection, Message message) {
        System.out.println("Request Handler SINGLE");
        messages.add(message);
        dispatch(message);
        System.out.println("----------------------------------------------------------------------------");
    }

    private void handleMessageWithTypeChannel(ClientConnection clientConnection, Message message) {
        System.out.println("Request Handler CHANNEL");
        if (channelServices.stream().noneMatch(channelService -> channelService.getName().equals(message.getChannel()))) {
            channelServices.add(new ChannelService(message.getChannel()));
            System.out.println(
                    new StringBuilder()
                            .append("Created channel with name ")
                            .append(message.getChannel())
            );
        } else {
            System.out.println(
                    new StringBuilder()
                            .append("Channel with that name ")
                            .append(message.getChannel())
                            .append("already exist")
            );
        }
    }

    private void handleMessageWithTypeClose(ClientConnection clientConnection, Message message) {
        System.out.println("Request Handler CLOSE");
        disconnect(clientConnection);
        System.out.println("----------------------------------------------------------------------------");
    }
}
