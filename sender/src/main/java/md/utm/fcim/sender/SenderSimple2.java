package md.utm.fcim.sender;

import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.connection.ServerConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.dto.User;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

public class SenderSimple2 {
    public static void main(String[] args) {
        System.out.println("Welcome to SenderSimple2");
        User user = new User("Simple2");
        Message message = new Message(MessageStatus.INIT, UserType.SENDER);
        ServerConnection serverConnection = CreateConnection.getINSTANCE().build(message, 4445).getServerConnection();
        while (true) {
            try {
                Thread.sleep(4000);
                Message msg = new Message(user, "message2", MessageStatus.SIMPLE);
                serverConnection.write(msg);
                System.out.println(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        serverConnection.close();
    }
}
