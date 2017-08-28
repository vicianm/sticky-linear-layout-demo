package com.github.vicianm.dynamicform.demo;

import android.app.Application;

import com.github.vicianm.dynamicform.demo.data.FormData;

public class DemoApplication extends Application {

    private FormData formData;

    @Override
    public void onCreate() {
        super.onCreate();
        formData = new FormData();
    }

    public FormData getFormData() {
        return formData;
    }

}
