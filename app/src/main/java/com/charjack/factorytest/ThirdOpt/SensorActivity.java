package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import java.util.Timer;
import java.util.TimerTask;

public class SensorActivity extends Activity implements View.OnClickListener{

    Button button_error,button_right;
    TextView x_value,y_value,z_value;
    TextView x_zheng_ok,x_fu_ok,y_zheng_ok,y_fu_ok,z_zheng_ok,z_fu_ok;
    TextView light_value,distance_value;
//    Timer timer;
    private SensorManager sensorManager;
    private MySensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_sensor);

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        x_value = (TextView) findViewById(R.id.x_value);
        y_value = (TextView) findViewById(R.id.y_value);
        z_value = (TextView) findViewById(R.id.z_value);
        x_zheng_ok = (TextView) findViewById(R.id.x_zheng_ok);
        x_fu_ok = (TextView) findViewById(R.id.x_fu_ok);
        y_zheng_ok = (TextView) findViewById(R.id.y_zheng_ok);
        y_fu_ok = (TextView) findViewById(R.id.y_fu_ok);
        z_zheng_ok = (TextView) findViewById(R.id.z_zheng_ok);
        z_fu_ok = (TextView) findViewById(R.id.z_fu_ok);
        light_value = (TextView) findViewById(R.id.light_value);
        distance_value = (TextView) findViewById(R.id.distance_value);

        sensorEventListener = new MySensorEventListener();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);

        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.SENSOR_NUM, intent);
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(BaseApp.autotest == 0){
            switch (v.getId()){
                case R.id.button_right:
                    intent.putExtra("str", Contents.RIGHT);
                    setResult(Contents.SENSOR_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str",Contents.ERROR);
                    setResult(Contents.SENSOR_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            System.out.println("autotest:--------------"+BaseApp.autotest);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("SENSOR", "1");
                    editor.apply();
                    intent.setClass(SensorActivity.this, BluetoothActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("SENSOR", "0");
                    editor.apply();
                    intent.setClass(SensorActivity.this, BluetoothActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    private final class MySensorEventListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                float x = event.values[SensorManager.DATA_X];
                float y = event.values[SensorManager.DATA_Y];
                float z = event.values[SensorManager.DATA_Z];

                x_value.setText(String.valueOf(x));
                y_value.setText(String.valueOf(y));
                z_value.setText(String.valueOf(z));

                if(x > 9.8f){
                    x_zheng_ok.setText("OK");
                }else if(x<-9.8f){
                    x_fu_ok.setText("OK");
                }

                if(y > 9.8f){
                    y_zheng_ok.setText("OK");
                }else if(y<-9.8f){
                    y_fu_ok.setText("OK");
                }

                if(z > 9.8f){
                    z_zheng_ok.setText("OK");
                }else if(z<-9.8f){
                    z_fu_ok.setText("OK");
                }

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
