package com.github.vicianm.stickylinearlayout.demo;

import android.app.Application;

import com.github.vicianm.stickylinearlayout.demo.data.AddressData;
import com.github.vicianm.stickylinearlayout.demo.data.FormData;
import com.github.vicianm.stickylinearlayout.demo.data.UserData;

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
