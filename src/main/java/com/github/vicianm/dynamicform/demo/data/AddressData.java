package com.github.vicianm.dynamicform.demo.data;

public class AddressData {

    private String country;

    private String city;

    private String street;

    private String streetNo;

    private String zip;

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
