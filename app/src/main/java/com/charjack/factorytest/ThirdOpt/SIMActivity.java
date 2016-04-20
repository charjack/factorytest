package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

public class SIMActivity extends Activity implements View.OnClickListener {
    //https://software.intel.com/zh-cn/blogs/2011/12/16/android-gsmcdma
    //http://blog.csdn.net/jingwen3699/article/details/8373183
    Button button_error,button_right,button_replay;
    TextView sim_state_value,sim_number_value,sim_degree_value;

    TelephonyManager tm;
    MyPhoneStateListener MyListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_sim);

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);
        if(BaseApp.autotest == 1){
            button_replay.setVisibility(View.VISIBLE);
        }

        sim_state_value = (TextView) findViewById(R.id.sim_state_value);
        sim_number_value = (TextView) findViewById(R.id.sim_number_value);
        sim_degree_value = (TextView) findViewById(R.id.sim_degree_value);

        tm = (TelephonyManager)this.getSystemService(TELEPHONY_SERVICE);
        switch(tm.getSimState()){  //getSimState()取得sim的状态  有下面6中状态
            case TelephonyManager.SIM_STATE_ABSENT :sim_state_value.setText("无卡");break;
            case TelephonyManager.SIM_STATE_UNKNOWN :sim_state_value.setText("未知状态");break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED :sim_state_value.setText("需要NetworkPIN解锁");break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED :sim_state_value.setText("需要PIN解锁");break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:sim_state_value.setText("需要PUK解锁");break;
            case TelephonyManager.SIM_STATE_READY:sim_state_value.setText("良好");break;
            default: sim_state_value.setText("未知状态");break;
        }
        sim_number_value.setText(tm.getSimCountryIso());

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        tm.listen(MyListener, PhoneStateListener.LISTEN_NONE);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        tm.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.SIM_NUM, intent);
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
                    setResult(Contents.SIM_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.SIM_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("SIM", "1");
                    editor.apply();
                    BaseApp.autotest = 0;
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("SIM", "0");
                    editor.apply();
                    BaseApp.autotest = 0;
                    finish();
                    break;
                case R.id.button_replay:
                    intent.setClass(SIMActivity.this, SIMActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

    }

    private class MyPhoneStateListener extends PhoneStateListener
    {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength)
        {
            super.onSignalStrengthsChanged(signalStrength);
            Toast.makeText(getApplicationContext(), "Go to Firstdroid!!! GSM Cinr = "
                    + String.valueOf(signalStrength.getGsmSignalStrength()), Toast.LENGTH_SHORT).show();

            sim_degree_value.setText(String.valueOf(signalStrength.getGsmSignalStrength()));
        }

    }
}
