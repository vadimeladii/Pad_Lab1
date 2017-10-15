package md.utm.fcim.common.enums;

public enum UserType {
    SENDER("SENDER"), RECEIVER("REVEIVER");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
