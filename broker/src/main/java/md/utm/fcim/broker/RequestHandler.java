package md.utm.fcim.broker;

import md.utm.fcim.common.Mesaj;

import java.io.IOException;

public class RequestHandler {

    public void add(ClientConnection clientConnection) {
        try {
            Mesaj mesaj = (Mesaj) clientConnection.getObjectInputStream().readObject();
            System.out.println(mesaj);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
