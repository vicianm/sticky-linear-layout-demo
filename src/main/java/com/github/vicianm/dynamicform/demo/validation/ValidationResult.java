package com.github.vicianm.dynamicform.demo.validation;

import android.view.View;

public class ValidationResult {

    public enum Validity {

        /**
         * Data has been validated and are valid.
         */
        VALID,

        /**
         * Data validation failed with errors.
         */
        INVALID,

        /**
         * Data are not complete enough to be validated.
         */
        UNKNOWN

    }

    public String message;

    public View item;

    public Validity validity;

    public ValidationResult(String message, View item, Validity validity) {
        this.message = message;
        this.item = item;
        this.validity = validity;
    }

    public String getMessage() {
        return message;
    }

    public View getItem() {
        return item;
    }

    public Validity getValidity() {
        return validity;
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "message='" + message + '\'' +
                ", validity=" + validity +
                '}';
    }
}
