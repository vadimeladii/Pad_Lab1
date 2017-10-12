package md.utm.fcim.common.connection;

import md.utm.fcim.common.dto.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ServerConnection {

    void write(Message message);

    void close();

    ObjectInputStream getObjectInputStream();

    ObjectOutputStream getObjectOutputStream();
}

