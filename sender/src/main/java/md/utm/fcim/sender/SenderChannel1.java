package md.utm.fcim.sender;

import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.dto.User;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

public class SenderChannel1 {
    public static void main(String[] args) {
        System.out.println("Welcome to SenderChannel1");
        User myUser = new User("SenderChannel1");
        Message mes = new Message(MessageStatus.INIT, UserType.SENDER);
        ServerConnection serverConnection = CreateConnection.getINSTANCE().build(mes, 4445).getServerConnection();
        serverConnection.write(new Message(MessageStatus.CHANNEL, "channel1"));
        while (true) {
            try {
                Thread.sleep(2000);
                Message msg = new Message(myUser, "message3", MessageStatus.SIMPLE, "channel1");
                serverConnection.write(msg);
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
