package com.charjack.factorytest.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Administrator on 2016/4/19.
 */
public class BaseApp extends Application{
    public static Context context;
    public static int autotest = 0;
    public static boolean ifdebug = false;
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        SharedPreferences mySharedPreferences = getSharedPreferences("ResultSave",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("VERSON", "NONE");
        editor.putString("BACKGROUND", "NONE");
        editor.putString("LCD", "NONE");
        editor.putString("BUTTON", "NONE");
        editor.putString("RECORDPLAY", "NONE");
        editor.putString("CAMERA", "NONE");
        editor.putString("TOUCH", "NONE");
        editor.putString("SENSOR", "NONE");
        editor.putString("BLUETOOTH", "NONE");
        editor.putString("WIFI", "NONE");
        editor.putString("GPS", "NONE");
        editor.putString("SDCARD", "NONE");
        editor.putString("SIM", "NONE");

        //提交当前数据
        editor.commit();

    }
}
