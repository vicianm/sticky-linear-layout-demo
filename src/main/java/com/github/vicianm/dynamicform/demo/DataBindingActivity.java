package com.github.vicianm.dynamicform.demo;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * <code>AppCompatActivity</code> with simple <code>TextView</code> data binding support.
 * @see #bindUiData(int, Object, String)
 */
public class DataBindingActivity extends AppCompatActivity {

    protected void bindUiData(int fieldId, final Object dataObject, final String fieldName) {
        TextView textView = (TextView)findViewById(fieldId);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Field declaredField = dataObject.getClass().getDeclaredField(fieldName);
                    declaredField.setAccessible(true);
                    declaredField.set(dataObject, s.toString());
                } catch (Throwable t) {
                    Log.e("DataBindingActivity", "Data binding failed", t);
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
