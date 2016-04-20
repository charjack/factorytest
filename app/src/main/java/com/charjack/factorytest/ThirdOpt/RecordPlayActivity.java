package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import java.io.IOException;

public class RecordPlayActivity extends ActionBarActivity implements View.OnClickListener {
    Button button_error,button_right,button_replay;
    Button button_record_start,button_record_stop,button_play_start,button_play_stop;

    private String fileName = null;
    private MediaPlayer mPlayer = null;
    private MediaRecorder mRecorder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_record_play);


        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);
        if(BaseApp.autotest == 1){
            button_replay.setVisibility(View.VISIBLE);
        }
        button_record_start = (Button) findViewById(R.id.button_record_start);
        button_record_stop = (Button) findViewById(R.id.button_record_stop);
        button_play_start = (Button) findViewById(R.id.button_play_start);
        button_play_stop = (Button) findViewById(R.id.button_play_stop);

        button_record_start.setOnClickListener(this);
        button_record_stop.setOnClickListener(this);
        button_play_start.setOnClickListener(this);
        button_play_stop.setOnClickListener(this);

        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";


    }

    @Override
    public void onClick(View v) {
        //http://blog.csdn.net/cxf7394373/article/details/8313980
        Intent intent = new Intent();
        SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (v.getId()){
            case R.id.button_right:
                if(BaseApp.autotest == 0){
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.RECORDPLAY_NUM, intent);
                    finish();
                } else if(BaseApp.autotest == 1){
                    editor.putString("RECORDPLAY", "1");
                    editor.apply();
                    intent.setClass(RecordPlayActivity.this,CameraActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.button_error:
                if(BaseApp.autotest == 0){
                    intent.putExtra("str",Contents.ERROR);
                    setResult(Contents.RECORDPLAY_NUM, intent);
                    finish();
                }else if(BaseApp.autotest == 1){
                    editor.putString("RECORDPLAY", "0");
                    editor.apply();
                    intent.setClass(RecordPlayActivity.this,CameraActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.button_replay:
                if(BaseApp.autotest == 1){
                    intent.setClass(RecordPlayActivity.this, RecordPlayActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.button_record_start:
                mRecorder = new MediaRecorder();
                mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                mRecorder.setOutputFile(fileName);
                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                try{
                    mRecorder.prepare();
                }catch (IOException e){
                    System.out.println("prepare() failed");
                }
                mRecorder.start();
                button_record_start.setVisibility(View.INVISIBLE);
                button_record_stop.setVisibility(View.VISIBLE);
                break;
            case R.id.button_record_stop:
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                button_record_stop.setVisibility(View.INVISIBLE);
                button_play_start.setVisibility(View.VISIBLE);
                break;
            case R.id.button_play_start:
                mPlayer = new MediaPlayer();
                try{
                    mPlayer.setDataSource(fileName);
                    mPlayer.prepare();
                    mPlayer.start();
                    button_play_start.setVisibility(View.INVISIBLE);
                    button_play_stop.setVisibility(View.VISIBLE);
                }catch(IOException e){
                }
                break;
            case R.id.button_play_stop:
                mPlayer.release();
                mPlayer = null;
                button_play_stop.setVisibility(View.INVISIBLE);
                button_record_start.setVisibility(View.VISIBLE);
                break;

        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str",Contents.ERROR);
        setResult(Contents.RECORDPLAY_NUM, intent);
        finish();
        return true;
    }
}
