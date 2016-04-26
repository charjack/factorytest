package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;
import com.charjack.factorytest.Utils.DrawImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CameraActivity extends Activity implements View.OnClickListener{
    Button button_error,button_right,button_replay;
    public View mLcdView;
    public View mNoSignal;
    public DrawImageView drawImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_camera);


        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);
//        if(BaseApp.autotest == 1){
//            button_replay.setVisibility(View.VISIBLE);
//        }

        drawImageView = (DrawImageView) findViewById(R.id.imageView);
        mNoSignal = findViewById(R.id.no_signal);

        mLcdView = findViewById(R.id.lcdtestview);
        mLcdView.setBackgroundColor(0xff050005);



    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            overridePendingTransition(R.anim.popup_in,R.anim.popup_out);

            if (msg.what == 1) {
                mNoSignal.setVisibility(View.INVISIBLE);
                drawImageView.setVisibility(View.VISIBLE);
                drawImageView.setDisplay(true);

                System.out.println("set paly");
            } else if (msg.what == 2) {
                mNoSignal.setVisibility(View.VISIBLE);
                drawImageView.setVisibility(View.VISIBLE);
                drawImageView.setDisplay(true);
                System.out.println("set no signal");
            }
        }

    };

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setVehicle(false);
        overridePendingTransition(R.anim.popup_in, R.anim.popup_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVehicle(true);

        handler.sendEmptyMessage(1); // sendMessage
    }

    public void setVehicle(boolean bOn) {
        File file = null;
        file = new File("/sys/class/misc/vehicle/auxEN");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (!file.exists()) {
            System.out.println("no such file");

        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            System.out.println("start set");

            if (fos != null) {
                if (bOn) {
                    System.out.println("set vehicle on");

                    fos.write(new String("1").getBytes());
                    fos.close();
                } else {
                    System.out.println("set vehicle off");
                    fos.write(new String("0").getBytes());
                    fos.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class DvThread implements Runnable {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                drawImageView.postInvalidate();
            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.CAMERA_NUM, intent);
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(BaseApp.autotest == 0) {
            switch (v.getId()) {
                case R.id.button_right:
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.CAMERA_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.CAMERA_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("CAMERA", "1");
                    editor.apply();
                    intent.setClass(CameraActivity.this, TouchActivity.class);
                    startActivity(intent);
                    onDestroy();
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("CAMERA", "0");
                    editor.apply();
                    intent.setClass(CameraActivity.this, TouchActivity.class);
                    startActivity(intent);
                    onDestroy();
                    finish();
                    break;
                case R.id.button_replay:
                    intent.setClass(CameraActivity.this, CameraActivity.class);
                    startActivity(intent);

                    onStop();
                    onDestroy();
                    finish();
                    break;
            }
        }

    }
}
