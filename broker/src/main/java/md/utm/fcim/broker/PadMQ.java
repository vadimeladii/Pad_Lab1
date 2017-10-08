package md.utm.fcim.broker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PadMQ {

    private ServerSocket serverSocket;
    private RequestHandler requestHandler;

    public PadMQ(Integer port) {
        requestHandler = new RequestHandler();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            try {
                Socket accept = serverSocket.accept();

                new Thread(() -> {
                    ClientConnection connection = new ClientConnection(accept);
                    requestHandler.add(connection);
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
