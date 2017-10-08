package md.utm.fcim.common;

import java.io.Serializable;

public class Mesaj implements Serializable {

    public String message;
    public String command;

    public Mesaj() {
    }

    public Mesaj(String message, String command) {
        this.message = message;
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "Mesaj{" +
                ", message='" + message + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
