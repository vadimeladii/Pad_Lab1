package md.utm.fcim.receiver.connection;

import md.utm.fcim.common.connection.impl.ServerConnectionImpl;

import java.net.Socket;

public class ReceiverConnection extends ServerConnectionImpl {

    public ReceiverConnection(Socket socket) {
        super(socket);
    }

}
