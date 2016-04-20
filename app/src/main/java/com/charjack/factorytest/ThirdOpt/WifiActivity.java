package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import java.util.ArrayList;
import java.util.List;

public class WifiActivity extends Activity implements View.OnClickListener {
    public List<String> lists = new ArrayList<>();
    Button button_error,button_right;
    ListView list_view;
    TextView start_wifi;
    WifiManager wifiManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_wifi);
        list_view = (ListView) findViewById(R.id.list_view);
        start_wifi = (TextView) findViewById(R.id.start_wifi);

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);  //open wifi
        start_wifi.setText("SUCCESS");
        initdata();

        Myadapter myadapter = new Myadapter(this,lists);
        list_view.setAdapter(myadapter);
    }

    private void initdata() {
        //http://blog.csdn.net/white__cat/article/details/41648403
       ArrayList<ScanResult> list = (ArrayList<ScanResult>) wifiManager.getScanResults();

        for(int i=0;i<list.size();i++)// order
            for(int j=1;j<list.size();j++)
            {
                if(list.get(i).level<list.get(j).level)    //
                {
                    ScanResult temp = null;
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        String wifiProperty;
        for(int i=0;i<list.size();i++){
            wifiProperty = null;
            wifiProperty = i+"."+" SSID:"+list.get(i).SSID+" BSSID:"+list.get(i).BSSID
                                +" capabilities:"+list.get(i).capabilities
                                +" level:"+list.get(i).level
                                +" frequency:"+list.get(i).frequency
                                +" timestamp:"+list.get(i).timestamp;
            lists.add(wifiProperty);
        }

}

    public void toggleWiFi(Context context, boolean enabled) {
        WifiManager wm = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(enabled);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.WIFI_NUM, intent);
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
                    setResult(Contents.WIFI_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.WIFI_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("WIFI", "1");
                    editor.apply();
                    intent.setClass(WifiActivity.this,GPSActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("WIFI", "0");
                    editor.apply();
                    intent.setClass(WifiActivity.this, GPSActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    public class Myadapter extends BaseAdapter {
        Context ctx;
        List<String>lists;
        public Myadapter(Context ctx ,List<String> lists){
            this.ctx = ctx;
            this.lists = lists;
        }
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder vh;
            if(convertView == null){
                convertView = LayoutInflater.from(ctx).inflate(R.layout.list_item_layout,null);
                vh = new ViewHolder();
                vh.textView = (TextView) convertView.findViewById(R.id.list_item);
                convertView.setTag(vh);
            }
            vh = (ViewHolder) convertView.getTag();
            vh.textView.setText(lists.get(position));

            return convertView;
        }


        class ViewHolder{
            TextView textView;
        }
    }
}
