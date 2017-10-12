package md.utm.fcim.common.connection.impl;

import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.enums.MessageStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnectionImpl implements ServerConnection {

    private Socket socket;
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
                    System.out.println(message);
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
