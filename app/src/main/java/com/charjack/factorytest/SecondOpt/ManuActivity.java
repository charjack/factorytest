package com.charjack.factorytest.SecondOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.charjack.factorytest.R;
import com.charjack.factorytest.ThirdOpt.BackgroundActivity;
import com.charjack.factorytest.ThirdOpt.BluetoothActivity;
import com.charjack.factorytest.ThirdOpt.ButtonActivity;
import com.charjack.factorytest.Contents;
import com.charjack.factorytest.ThirdOpt.CameraActivity;
import com.charjack.factorytest.ThirdOpt.GPSActivity;
import com.charjack.factorytest.ThirdOpt.LCDActivity;
import com.charjack.factorytest.ThirdOpt.RecordPlayActivity;
import com.charjack.factorytest.ThirdOpt.SDCardActivity;
import com.charjack.factorytest.ThirdOpt.SIMActivity;
import com.charjack.factorytest.ThirdOpt.SensorActivity;
import com.charjack.factorytest.ThirdOpt.TouchActivity;
import com.charjack.factorytest.ThirdOpt.VersonActivity;
import com.charjack.factorytest.ThirdOpt.WifiActivity;
import com.charjack.factorytest.Utils.BaseApp;


public class ManuActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_manu);

        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button21).setOnClickListener(this);
        findViewById(R.id.button22).setOnClickListener(this);
        findViewById(R.id.button23).setOnClickListener(this);
        findViewById(R.id.button31).setOnClickListener(this);
        findViewById(R.id.button32).setOnClickListener(this);
        findViewById(R.id.button33).setOnClickListener(this);
        findViewById(R.id.button41).setOnClickListener(this);
        findViewById(R.id.button42).setOnClickListener(this);
        findViewById(R.id.button43).setOnClickListener(this);
        findViewById(R.id.button51).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.button11:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"VERSON TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, VersonActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button12:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"BEIGUANG TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, BackgroundActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button13:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"LCD TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, LCDActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button21:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"BUTTON TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, ButtonActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button22:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"RECORD PLAY TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, RecordPlayActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button23:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"CAMERA TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, CameraActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button31:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"TOUCH TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, TouchActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button32:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"SENSOR TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, SensorActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button33:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"BLUETOOTH TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, BluetoothActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button41:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"WIFI TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, WifiActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button42:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"GPS TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, GPSActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button43:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"SDCARD TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, SDCardActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.button51:
                if(BaseApp.ifdebug == true)
                    Toast.makeText(getApplicationContext(),"SIM TEST",Toast.LENGTH_SHORT).show();
                intent.setClass(ManuActivity.this, SIMActivity.class);
                startActivityForResult(intent, 0);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle b = data.getExtras();
        String str = b.getString("str");
        SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch(resultCode){
           case Contents.VERSON_NUM:
               editor.putString("VERSON", str);
               editor.apply();
               if(BaseApp.ifdebug)
                   Toast.makeText(getApplicationContext(),"VERSON-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           case Contents.BEIGUANG_NUM:
               editor.putString("BACKGROUND", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"BACKGROUND-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           case Contents.LCD_NUM:
               editor.putString("LCD", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"LCD-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           case Contents.BUTTON_NUM:
               editor.putString("BUTTON", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"BUTTON-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           case Contents.RECORDPLAY_NUM:
               editor.putString("RECORDPLAY", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"RECORDPLAY-----------"+str,Toast.LENGTH_SHORT).show();
               break;
            case Contents.CAMERA_NUM:
                editor.putString("CAMERA", str);
                editor.apply();
                if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"CAMERA-----------"+str,Toast.LENGTH_SHORT).show();
                break;
           case Contents.TOUCH_NUM:
               editor.putString("TOUCH", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"TOUCH-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           case Contents.SENSOR_NUM:
               editor.putString("SENSOR", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"SENSOR-----------"+str,Toast.LENGTH_SHORT).show();
               break;
            case Contents.BLUETOOTH_NUM:
                editor.putString("BLUETOOTH", str);
                editor.apply();
                if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"BLUETOOTH-----------"+str,Toast.LENGTH_SHORT).show();
                break;
           case Contents.WIFI_NUM:
               editor.putString("WIFI", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"WIFI-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           case Contents.GPS_NUM:
               editor.putString("GPS", str);
               editor.apply();
               if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"GPS-----------"+str,Toast.LENGTH_SHORT).show();
               break;
            case Contents.SDCARD_NUM:
                editor.putString("SDCARD", str);
                editor.apply();
                if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"SDCARD-----------"+str,Toast.LENGTH_SHORT).show();
                break;
           case Contents.SIM_NUM:
               editor.putString("SIM", str);
               editor.apply();
               if(BaseApp.ifdebug)
               Toast.makeText(getApplicationContext(),"SIM-----------"+str,Toast.LENGTH_SHORT).show();
               break;
           default:
               break;
       }
    }
}
