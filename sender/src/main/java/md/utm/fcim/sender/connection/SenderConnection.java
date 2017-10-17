package md.utm.fcim.sender.connection;

import md.utm.fcim.common.connection.impl.ServerConnectionImpl;

import java.net.Socket;

public class SenderConnection extends ServerConnectionImpl {

    public SenderConnection(Socket socket) {
        super(socket);
    }

}
