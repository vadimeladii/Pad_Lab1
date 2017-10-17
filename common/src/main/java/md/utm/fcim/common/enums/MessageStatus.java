package md.utm.fcim.common.enums;

public enum MessageStatus {
    INIT("INIT"), CLOSE("CLOSE"), SIMPLE("SIMPLE"), CHANNEL("CHANNEL");

    private String status;

    MessageStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
