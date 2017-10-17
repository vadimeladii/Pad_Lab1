package md.utm.fcim.common.connection.impl;

import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.enums.MessageStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnectionImpl implements ServerConnection {

    private Socket socket;
    private List<String> subscribers = new ArrayList<>();
    protected ObjectInputStream objectInputStream;
    protected ObjectOutputStream objectOutputStream;

    public ServerConnectionImpl(Socket socket) {
        try {
            this.socket = socket;
            objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(messageListener()).start();
    }

    private Runnable messageListener() {
        return () -> {
            while (!this.socket.isClosed()) {
                try {
                    Message message = (Message) getObjectInputStream().readObject();
                    if(message.getChannel() == null) {
                        System.out.println("message -> " + message.getMessage() + ": user_name -> " + message.getUser().getName());
                    }
                    subscribers.forEach(subscriber -> {
                        if (subscriber.equals(message.getChannel())) {
                            System.out.println(message);
                        }
                    });
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Server connection interrupted.");
                    try {
                        this.socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
    }

    public void write(Message message) {
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToChannel(String channelName) {
        subscribers.add(channelName);
    }

    public void close() {
        Message message = new Message();
        message.setMessageStatus(MessageStatus.CLOSE);
        write(message);
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
