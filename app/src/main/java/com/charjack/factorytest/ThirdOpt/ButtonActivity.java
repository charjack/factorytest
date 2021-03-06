package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

public class ButtonActivity extends ActionBarActivity implements View.OnClickListener{

    Button button_error,button_right,button_replay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_button);


        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);
        if(BaseApp.autotest == 1){
            button_replay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(BaseApp.autotest == 0){
            switch (v.getId()){
                case R.id.button_right:
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.BUTTON_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str",Contents.ERROR);
                    setResult(Contents.BUTTON_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("BUTTON", "1");
                    editor.apply();
                    intent.setClass(ButtonActivity.this,RecordPlayActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("BUTTON", "0");
                    editor.apply();
                    intent.setClass(ButtonActivity.this, RecordPlayActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_replay:
                    intent.setClass(ButtonActivity.this, ButtonActivity.class);
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
        setResult(Contents.BUTTON_NUM, intent);
        finish();
        return true;
    }
}
