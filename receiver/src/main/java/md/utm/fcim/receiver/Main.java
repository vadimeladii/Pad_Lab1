package md.utm.fcim.receiver;

import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.dto.User;
import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Receiver");
        User myUser = new User("receiver1");
        Message mes = new Message(myUser, "receiver", MessageStatus.INIT, UserType.RECEIVER);
        CreateConnection.getINSTANCE().build(mes, 4445);
    }
}
