package com.github.vicianm.stickylinearlayout.demo.validation;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.stickylinearlayout.demo.R;
import com.github.vicianm.stickylinearlayout.demo.data.UserData;

import java.util.LinkedList;
import java.util.List;

public class UserValidator implements Validator {

    protected ValidationCallback callback;

    protected UserData userData;

    protected View ui;

    public UserValidator(ValidationCallback callback, UserData userData, View ui) {
        this.callback = callback;
        this.userData = userData;
        this.ui = ui;
    }

    @Override
    public void validate() {

        List<ValidationResult> result = new LinkedList<>();
        result.add(validateTextViewNotNull(userData.name, ui, R.id.user_name));
        result.add(validateTextViewNotNull(userData.surname, ui, R.id.user_surname));
        result.add(validateId(userData.id, ui, R.id.user_id));

        callback.validated(result);
    }

    protected ValidationResult validateId(String dataString, View ui, int textViewId) {

        TextView textView = (TextView) ui.findViewById(textViewId);

        if (TextUtils.isEmpty(dataString)) {
            textView.setError(null);
            return new ValidationResult(textView, ValidationResult.Validity.UNKNOWN);

        } else {
            if (dataString.length() != 6) {
                textView.setError("Please type in 6 digits ID");
                return new ValidationResult(textView, ValidationResult.Validity.ERROR);
            }
            try {
                Integer.parseInt(dataString);
            } catch (Throwable t) {
                textView.setError("Please type in 6 digits ID");
                return new ValidationResult(textView, ValidationResult.Validity.ERROR);
            }
            textView.setError(null);
            return new ValidationResult(textView, ValidationResult.Validity.VALID);
        }
    }

    protected ValidationResult validateTextViewNotNull(String dataString, View ui, int textViewId) {
        TextView textView = (TextView) ui.findViewById(textViewId);
        if (TextUtils.isEmpty(dataString)) {
            return new ValidationResult(textView, ValidationResult.Validity.UNKNOWN);
        } else {
            return new ValidationResult(textView, ValidationResult.Validity.VALID);
        }
    }

}
