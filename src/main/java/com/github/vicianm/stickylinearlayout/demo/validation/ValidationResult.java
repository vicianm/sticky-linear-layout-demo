package com.github.vicianm.stickylinearlayout.demo.validation;

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
        ERROR,

        /**
         * Data are not complete enough to be validated.
         */
        UNKNOWN

    }

    public View item;

    public Validity validity;

    public ValidationResult(View item, Validity validity) {
        this.item = item;
        this.validity = validity;
    }

    public View getItem() {
        return item;
    }

    public Validity getValidity() {
        return validity;
    }

}
