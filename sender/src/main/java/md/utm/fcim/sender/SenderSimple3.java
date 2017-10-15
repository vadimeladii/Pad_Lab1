package md.utm.fcim.sender;

import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.dto.User;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

public class SenderSimple3 {
    public static void main(String[] args) {
        System.out.println("Welcome to SenderSimple1");
        User myUser = new User("vadim3");
        Message mes = new Message(myUser, "hi3", MessageStatus.INIT, UserType.SENDER);
        ServerConnection serverConnection = CreateConnection.getINSTANCE().build(mes, 4445).getServerConnection();
        serverConnection.write(new Message(myUser, "create channel", MessageStatus.CHANNEL, "channel2"));
        while (true) {
            try {
                Thread.sleep(5000);
                serverConnection.write(new Message(myUser, "message3", MessageStatus.SINGLE, "channel2"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
