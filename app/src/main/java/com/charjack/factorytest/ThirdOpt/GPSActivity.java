package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.util.Iterator;

public class GPSActivity extends Activity implements View.OnClickListener {
//http://www.2cto.com/kf/201503/383394.html
 //   http://www.cnblogs.com/linjiqin/archive/2011/11/01/2231598.html
    Button button_error,button_right;
    LocationManager lm;
    TextView longitude_view,latitude_view;
    //状态监听
    GpsStatus.Listener listener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            switch (event) {
                //第一次定位
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    System.out.println("第一次定位");
                    break;
                //卫星状态改变
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    System.out.println("卫星状态改变");
                    //获取当前状态
                    GpsStatus gpsStatus=lm.getGpsStatus(null);
                    //获取卫星颗数的默认最大值
                    int maxSatellites = gpsStatus.getMaxSatellites();
                    //创建一个迭代器保存所有卫星
                    Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                    int count = 0;
                    while (iters.hasNext() && count <= maxSatellites) {
                        GpsSatellite s = iters.next();
                        count++;
                    }
                    System.out.println("搜索到:"+count+"颗卫星");
                    break;
                ////定位启动
                case GpsStatus.GPS_EVENT_STARTED:
                    System.out.println("//定位启动");
                    break;
                //定位结束
                case GpsStatus.GPS_EVENT_STOPPED:
                    System.out.println("定位结束");
                    break;
            }
        };
    };
    private LocationListener locationListener=new LocationListener() {

        /**
         * 位置信息变化时触发
         */
        public void onLocationChanged(Location location) {
            updateView(location);
            if(BaseApp.ifdebug)
                System.out.println("时间" + location.getTime() + "经度" + location.getLongitude() + "纬度" + location.getLatitude() + "海拔" + location.getAltitude());
        }

        /**
         *  GPS状态变化时触发
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    System.out.println("当前GPS状态为可见状态");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    System.out.println("当前GPS状态为服务区外状态");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    System.out.println("当前GPS状态为暂停服务状态");
                    break;
            }
        }

        /**
         * GPS开启时触发
         */
        public void onProviderEnabled(String provider) {
            Location location=lm.getLastKnownLocation(provider);
            updateView(location);
        }

        /**
         * GPS禁用时触发
         */
        public void onProviderDisabled(String provider) {
            updateView(null);
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_gps);

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        longitude_view = (TextView) findViewById(R.id.longitude_view);
        latitude_view = (TextView) findViewById(R.id.latitude_view);


        lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        if(!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent,0);
            return;
        }

        String bestProvider = lm.getBestProvider(getCriteria(), true);
        Location location= lm.getLastKnownLocation(bestProvider);
        updateView(location);
        lm.addGpsStatusListener(listener);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
    }

    /**
     * 实时更新文本内容
     * @return
     */
    private Criteria getCriteria(){
        Criteria criteria=new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    private void updateView(Location location){
        if(location!=null){
            longitude_view.setText("longitude:");
            longitude_view.append(String.valueOf(location.getLongitude()));
            latitude_view.setText("latitude:");
            latitude_view.append(String.valueOf(location.getLatitude()));
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        lm.removeUpdates(locationListener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.GPS_NUM, intent);
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
                    setResult(Contents.GPS_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.GPS_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("GPS", "1");
                    editor.apply();
                    intent.setClass(GPSActivity.this,SDCardActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("GPS", "0");
                    editor.apply();
                    intent.setClass(GPSActivity.this, SDCardActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

}
