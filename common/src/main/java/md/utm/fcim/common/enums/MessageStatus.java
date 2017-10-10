package md.utm.fcim.common.enums;

public enum MessageStatus {
    INIT("INIT"), CLOSE("CLOSE"), DATA("DATA");

    private String status;

    MessageStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
