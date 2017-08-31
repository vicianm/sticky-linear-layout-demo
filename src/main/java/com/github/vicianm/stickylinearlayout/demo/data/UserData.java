package com.github.vicianm.stickylinearlayout.demo.data;

public class UserData {

    public String name;

    public String surname;

    public String id;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(name);
        }
        if (surname != null) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(surname);
        }
        if (id != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(id);
        }
        return sb.toString();
    }

}
