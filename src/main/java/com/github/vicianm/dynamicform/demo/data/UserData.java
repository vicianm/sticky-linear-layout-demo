package com.github.vicianm.dynamicform.demo.data;

public class UserData {

    private String name;

    private String surname;

    private String id;

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
