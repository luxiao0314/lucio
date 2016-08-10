package com;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.util.SpUtil;

/**
 * Created by baixiaokang on 16/4/23.
 */
public class BaseApplication extends Application {
    private static BaseApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        SpUtil.init(this);
    }

    public static Context getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }

}
