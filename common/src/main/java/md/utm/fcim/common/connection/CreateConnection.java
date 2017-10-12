package md.utm.fcim.common.connection;

import md.utm.fcim.common.connection.impl.ServerConnectionImpl;
import md.utm.fcim.common.dto.Message;

import java.io.IOException;
import java.net.Socket;

public class CreateConnection {

    private static CreateConnection INSTANCE;
    private Socket socket;
    private ServerConnection serverConnection;

    public static CreateConnection getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CreateConnection();
        }
        return INSTANCE;
    }

    public CreateConnection build(Message message, Integer port) {
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverConnection = new ServerConnectionImpl(socket);
        serverConnection.write(message);
        return this;
    }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }
}
