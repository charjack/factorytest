package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import java.util.Timer;
import java.util.TimerTask;

public class LCDActivity extends Activity implements View.OnClickListener {

    public int recLen = 0;
    TextView lcd_textview;
    TextView lcd_textview_show;
    LinearLayout lcd_button_select;
    Button button_error,button_right,button_replay;
    Timer timer;
     final Handler handler = new Handler(){
             public void handleMessage(Message msg) {
                     switch (msg.what) {
                             case 1:
                                 recLen++;
                                 lunxu(recLen);
                                 break;
                             }
                         super.handleMessage(msg);
                     }
             };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lcd);

        lcd_textview = (TextView) findViewById(R.id.lcd_textview);
        lcd_textview_show = (TextView) findViewById(R.id.lcd_textview_show);
        lcd_textview_show.setOnClickListener(this);
        lcd_button_select = (LinearLayout) findViewById(R.id.lcd_button_select);


        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);
        if(BaseApp.autotest == 1){
            button_replay.setVisibility(View.VISIBLE);
        }
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                  message.what = 1;
                 handler.sendMessage(message);
                }
        },1500,1500);

    }

    private void lunxu(int recLen) {

        switch(recLen) {
            case 1:
                lcd_textview.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                lcd_textview.setBackgroundColor(Color.BLUE);
                break;
            case 3:
                lcd_textview.setBackgroundColor(Color.WHITE);
                break;
            case 4:
                lcd_textview.setBackgroundColor(Color.BLACK);
                break;
            case 5:
                lcd_textview_show.setVisibility(View.VISIBLE);
                lcd_button_select.setVisibility(View.VISIBLE);
                recLen = 0;
                timer.cancel();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(BaseApp.autotest == 0) {
            switch (v.getId()) {
                case R.id.button_right:
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.LCD_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.LCD_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("LCD", "1");
                    editor.apply();
                    intent.setClass(LCDActivity.this,ButtonActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("LCD", "0");
                    editor.apply();
                    intent.setClass(LCDActivity.this, ButtonActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_replay:
                    intent.setClass(LCDActivity.this, LCDActivity.class);
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
        setResult(Contents.LCD_NUM, intent);
        finish();
        return true;
    }
}
