package com.github.vicianm.dynamicform.demo;

import android.app.Application;

import com.github.vicianm.dynamicform.demo.data.AddressData;
import com.github.vicianm.dynamicform.demo.data.FormData;
import com.github.vicianm.dynamicform.demo.data.UserData;

public class DemoApplication extends Application {

    private FormData formData;

    @Override
    public void onCreate() {
        super.onCreate();
        formData = new FormData();
        formData.setUser01(new UserData());
        formData.setAddress01(new AddressData());
        formData.setUser02(new UserData());
        formData.setAddress02(new AddressData());
        formData.setUser03(new UserData());
        formData.setAddress03(new AddressData());
    }

    public FormData getFormData() {
        return formData;
    }

}
