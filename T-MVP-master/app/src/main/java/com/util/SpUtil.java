package com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.base.ex.ExBaseActivity;
import com.base.Constants;
import com.data.entity._User;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/4/5.
 */
public class SpUtil {
    static SharedPreferences prefs;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isNight() {
        return prefs.getBoolean("isNight", false);
    }

    public static void setNight(Context context, boolean isNight) {
        prefs.edit().putBoolean("isNight", isNight).commit();
        if (context instanceof ExBaseActivity)
            ((ExBaseActivity) context).reload();
    }

    public static _User getUser() {
        return new Gson().fromJson(prefs.getString("user", ""), _User.class);
    }

    public static void setUser(_User user) {
        prefs.edit().putString("user", new Gson().toJson(user)).commit();
    }

    public static void putBoolean(String key, boolean value) {
        if (prefs == null) {
            prefs = mContext.getSharedPreferences(Constants.SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }
        prefs.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key,boolean defaultValue) {
        if (prefs == null) {
            prefs = mContext.getSharedPreferences(Constants.SHARE_PREFS_NAME,
                    Context.MODE_PRIVATE);
        }

        return prefs.getBoolean(key, defaultValue);
    }
}
