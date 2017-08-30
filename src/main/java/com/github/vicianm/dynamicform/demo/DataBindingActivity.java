package com.github.vicianm.dynamicform.demo;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.vicianm.dynamicform.demo.validation.Validator;

import java.lang.reflect.Field;

/**
 * <code>AppCompatActivity</code> with simple <code>TextView</code> data binding support.
 * @see #bindUiData(View, int, Object, String, Validator)
 */
public class DataBindingActivity extends AppCompatActivity {

    protected void bindUiData(View container, int containerFieldId, final Object dataObject, final String dataObjectFieldName, final Validator validator) {

        TextView textView = (TextView)container.findViewById(containerFieldId);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // Bind data
                try {
                    Field declaredField = dataObject.getClass().getDeclaredField(dataObjectFieldName);
                    declaredField.setAccessible(true);
                    declaredField.set(dataObject, s.toString());
                } catch (Throwable t) {
                    Log.e("DataBindingActivity", "Data binding failed", t);
                }

                // Validate data
                if (validator != null) {
                    validator.validate();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

}
