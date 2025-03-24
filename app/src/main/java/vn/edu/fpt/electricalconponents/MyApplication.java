package vn.edu.fpt.electricalconponents;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication insatnce;

    public static Context getInstance() {
        return insatnce.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        insatnce = this;
    }
}
