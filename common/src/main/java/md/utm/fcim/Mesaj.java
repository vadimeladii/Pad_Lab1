package md.utm.fcim;

public class Mesaj {

    private String name;
    private String message;
    private String command;

    public Mesaj() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
