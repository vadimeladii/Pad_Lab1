package md.utm.fcim.sender;

import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.dto.User;
import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Sender");
        User myUser = new User("vadim");
        Message mes = new Message(myUser, "hi", MessageStatus.INIT, UserType.SENDER);
        ServerConnection serverConnection = CreateConnection.getINSTANCE().build(mes, 4445).getServerConnection();
        serverConnection.write(new Message(myUser, "createChannel1", MessageStatus.CHANNEL, "channel1"));
        serverConnection.write(new Message(myUser, "createChannel2", MessageStatus.CHANNEL, "channel2"));
        serverConnection.write(new Message(myUser, "message1", MessageStatus.SINGLE, "channel1"));
        serverConnection.write(new Message(myUser, "message2", MessageStatus.SINGLE, "channel2"));
        serverConnection.close();
    }
}
