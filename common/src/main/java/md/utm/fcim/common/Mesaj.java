package md.utm.fcim.common;

public class Mesaj {

    public String message;
    public String command;

    public Mesaj(String send, String mymes) {
        this.command = send;
        this.message = mymes;
    }

    @Override
    public String toString() {
        return "Mesaj{" +
                ", message='" + message + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
