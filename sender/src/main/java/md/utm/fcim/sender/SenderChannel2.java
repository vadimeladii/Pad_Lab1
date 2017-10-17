package md.utm.fcim.sender;

import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.dto.User;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

public class SenderChannel2 {

    public static void main(String[] args) {
        System.out.println("Welcome to SenderChannel2");
        User myUser = new User("SenderChannel2");
        Message mes = new Message(MessageStatus.INIT, UserType.SENDER);
        ServerConnection serverConnection = CreateConnection.getINSTANCE().build(mes, 4445).getServerConnection();
        serverConnection.write(new Message(MessageStatus.CHANNEL, "channel2"));
        while (true) {
            try {
                Thread.sleep(2000);
                Message msg = new Message(myUser, "message4", MessageStatus.SIMPLE, "channel2");
                serverConnection.write(msg);
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
