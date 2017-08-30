package com.github.vicianm.dynamicform.demo.data;

public class AddressData {

    public String country;

    public String city;

    public String street;

    public String streetNo;

    public String zip;

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        if (street != null) {
            sb.append(street);
        }
        if (streetNo != null) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(streetNo);
        }
        if (city != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(city);
        }
        if (zip != null) {
            if (city != null && sb.length() > 0) {
                sb.append(" ");
            } else if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(zip);
        }
        if (country != null) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(country);
        }
        return sb.toString();
    }
}
