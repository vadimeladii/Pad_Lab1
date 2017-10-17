package md.utm.fcim.receiver;

import md.utm.fcim.common.connection.CreateConnection;
import md.utm.fcim.common.dto.Message;
import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

public class ReceiverChannel1 {
    public static void main(String[] args) {
        System.out.println("Welcome to Receiver");
        Message mes = new Message(MessageStatus.INIT, UserType.RECEIVER);
        CreateConnection.getINSTANCE().build(mes, 4445)
                .getServerConnection()
                .subscribeToChannel("channel1");
    }
}
