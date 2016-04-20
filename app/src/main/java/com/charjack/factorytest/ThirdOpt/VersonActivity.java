package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VersonActivity extends ActionBarActivity implements View.OnClickListener {

    Button button_error,button_right,button_replay;
    TextView versonid_value,creattime_value;

    public static String getAPPtime(Context ctx){
        SimpleDateFormat formatter =  new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    public static String getAPPVersionCodeFromAPP(Context ctx) {
        int currentVersionCode = 0;
        String appVersionName = null;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            appVersionName = info.versionName; //
            currentVersionCode = info.versionCode; //
            System.out.println(currentVersionCode + " " + appVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch blockd
            e.printStackTrace();
        }
        return appVersionName+currentVersionCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_verson);

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);
        if(BaseApp.autotest == 1){
            button_replay.setVisibility(View.VISIBLE);
        }
        versonid_value = (TextView) findViewById(R.id.versonid_value);
        creattime_value = (TextView) findViewById(R.id.creattime_value);
        versonid_value.setText(getAPPVersionCodeFromAPP(this));
        creattime_value.setText(getAPPtime(this));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(BaseApp.autotest == 0){
            switch (v.getId()){
                case R.id.button_right:
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.VERSON_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str",Contents.ERROR);
                    setResult(Contents.VERSON_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("VERSON", "1");
                    editor.apply();
                    intent.setClass(VersonActivity.this, BackgroundActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("VERSON", "0");
                    editor.apply();
                    intent.setClass(VersonActivity.this, BackgroundActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_replay:
                    intent.setClass(VersonActivity.this, VersonActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str",Contents.ERROR);
        setResult(Contents.VERSON_NUM, intent);
        finish();
        return true;
    }

}
