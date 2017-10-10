package md.utm.fcim.common.dto;

import md.utm.fcim.common.enums.MessageStatus;
import md.utm.fcim.common.enums.UserType;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = -8524201840600026558L;

    private User user;
    private String message;
    private MessageStatus messageStatus;
    private UserType userType;

    public Message() {
    }

    public Message(User user, String message, MessageStatus messageStatus, UserType userType) {
        this.user = user;
        this.message = message;
        this.messageStatus = messageStatus;
        this.userType = userType;
    }

    public Message(User user, String message, MessageStatus messageStatus) {
        this.user = user;
        this.message = message;
        this.messageStatus = messageStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", messageStatus=" + messageStatus +
                ", userType=" + userType +
                '}';
    }
}
