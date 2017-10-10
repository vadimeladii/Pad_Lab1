package md.utm.fcim.common.connection;

import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.enums.MessageStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ServerConnection(Socket socket) {
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
            while (!getSocket().isClosed()) {
                try {
                    Message message = (Message) getObjectInputStream().readObject();
                    System.out.println(message);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Server connection interrupted.");
                    try {
                        getSocket().close();
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

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

}

