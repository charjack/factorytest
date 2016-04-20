package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import java.util.Timer;
import java.util.TimerTask;

public class BackgroundActivity extends ActionBarActivity implements View.OnClickListener {

    Timer timer;
    Button button_error,button_right;
    WindowManager.LayoutParams lp;
    final Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    lp = getWindow().getAttributes();
                    lp.screenBrightness += 0.1;
                    if(lp.screenBrightness > 1.0)
                        lp.screenBrightness  = (float)(lp.screenBrightness - 1.0);

                    getWindow().setAttributes(lp);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_background);

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);

//        lp = getWindow().getAttributes();
//        lp.screenBrightness = 0.0f;
//        getWindow().setAttributes(lp);

        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 1000, 1000);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(BaseApp.autotest == 0) {
            switch (v.getId()) {
                case R.id.button_right:
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.BEIGUANG_NUM, intent);
                    timer.cancel();
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.BEIGUANG_NUM, intent);
                    timer.cancel();
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("BACKGROUND", "1");
                    editor.apply();
                    intent.setClass(BackgroundActivity.this, LCDActivity.class);
                    startActivity(intent);
                    finish();

                    break;
                case R.id.button_error:
                    editor.putString("BACKGROUND", "0");
                    editor.apply();
                    intent.setClass(BackgroundActivity.this, LCDActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.BEIGUANG_NUM, intent);
        timer.cancel();
        finish();
        return true;
    }
}
