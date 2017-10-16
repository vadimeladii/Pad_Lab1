package md.utm.fcim.common.dto;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -1234201840600026558L;

    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
