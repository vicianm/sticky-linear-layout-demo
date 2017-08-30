package com.github.vicianm.dynamicform.demo.validation;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.dynamicform.demo.R;
import com.github.vicianm.dynamicform.demo.data.AddressData;

import java.util.LinkedList;
import java.util.List;

public class AddressValidator implements Validator {

    protected ValidationCallback callback;

    protected AddressData addressData;

    protected View ui;

    public AddressValidator(ValidationCallback callback, AddressData addressData, View ui) {
        this.callback = callback;
        this.addressData = addressData;
        this.ui = ui;
    }

    @Override
    public void validate() {

        List<ValidationResult> result = new LinkedList<>();
        result.add(validateTextViewNotNull(addressData.country, ui, R.id.address_country));
        result.add(validateTextViewNotNull(addressData.city, ui, R.id.address_city));
        result.add(validateTextViewNotNull(addressData.street, ui, R.id.address_street));
        result.add(validateTextViewNotNull(addressData.streetNo, ui, R.id.address_street_no));
        result.add(validateTextViewNotNull(addressData.zip, ui, R.id.address_zip));

        callback.validated(result);
    }

    protected ValidationResult validateTextViewNotNull(String dataString, View ui, int textViewId) {
        TextView textView = (TextView) ui.findViewById(textViewId);
        if (TextUtils.isEmpty(dataString)) {
            return new ValidationResult(null, textView, ValidationResult.Validity.UNKNOWN);
        } else {
            return new ValidationResult(null, textView, ValidationResult.Validity.VALID);
        }
    }

}
