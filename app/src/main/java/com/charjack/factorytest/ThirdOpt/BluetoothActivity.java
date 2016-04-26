package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BluetoothActivity extends ActionBarActivity implements View.OnClickListener{
    Button button_error,button_right,button_replay;
    BluetoothReceiver bluetoothReceiver;
    Context ctx;

    TextView bluetooth_state;
    ListView bluetooth_list;
    List<String> lists;
    MyBluetoothAdapter myBluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_bluetooth);

        ctx = this;

        button_right = (Button) findViewById(R.id.button_right);
        button_error = (Button) findViewById(R.id.button_error);
        button_replay = (Button) findViewById(R.id.button_replay);
        button_right.setOnClickListener(this);
        button_error.setOnClickListener(this);
        button_replay.setOnClickListener(this);

        bluetooth_state = (TextView) findViewById(R.id.bluetooth_state);
        bluetooth_list = (ListView) findViewById(R.id.bluetooth_list);

        if(BaseApp.autotest == 1){
            button_replay.setVisibility(View.VISIBLE);
        }

        bluetoothReceiver = new BluetoothReceiver();
        //发送广播
        Intent intent = new Intent("com.wedesign.action.FACTORY_BLUETOOTH_SERVER");
        intent.putExtra("bluetooth", "start"); //启动蓝牙
        this.sendBroadcast(intent);
        lists = new ArrayList<>();
        lists.add("蓝牙:设备名称---MAC地址");
        myBluetoothAdapter = new MyBluetoothAdapter(this, lists);
        bluetooth_list.setAdapter(myBluetoothAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册接受的广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.wedesign.action.FACTORY_BLUETOOTH_CLIENT");
        registerReceiver(bluetoothReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(bluetoothReceiver);
    }

//    蓝牙广播接收器
    public class BluetoothReceiver extends BroadcastReceiver{
        Context ctx;
        public BluetoothReceiver(){}
        public BluetoothReceiver(Context context){
            this.ctx = context;
        };

        @Override
        public void onReceive(Context context, Intent intent) {
        //    Toast.makeText(context, "接收一个蓝牙的广播", Toast.LENGTH_SHORT).show();
            //todo.
            //

            String info = intent.getStringExtra("bluetooth");
            if(info == null){
                info = "nodevice";
            }

            if(info.equals("start")){
                //开启线程去执行
             //   Toast.makeText(context, info+"---", Toast.LENGTH_SHORT).show();
                Message msg1= new Message();
                msg1.what = 1;
                handler2.sendMessage(msg1);
            }else if(info.equals("stop")){
                //开启线程去执行
                Message msg3= new Message();
                msg3.what = 3;
                handler2.sendMessage(msg3);
            }


            String device = intent.getStringExtra("bluetoothdevice");
            if(device != null){
                Message msg2= new Message();
                msg2.what = 2;
                Bundle bundle = new Bundle();
                bundle.putString("text",device);
                msg2.setData(bundle);
                handler2.sendMessage(msg2);
            }



        }
    }


    Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                switch(msg.what) {
                    case 1:
                        Toast.makeText(getApplicationContext(), "蓝牙已经打开，正在搜索蓝牙设备...", Toast.LENGTH_SHORT).show();
                        bluetooth_state.setText("蓝牙已经打开，正在搜索蓝牙设备...");
                        Intent intent2 = new Intent("com.wedesign.action.FACTORY_BLUETOOTH_SERVER");
                        intent2.putExtra("bluetooth","device"); //搜索蓝牙设备
                        ctx.sendBroadcast(intent2);
                        break;
                    case 2:
                        String str = msg.getData().getString("text");
                        if(str!=null)
                        {
                            lists.add(str);
                            myBluetoothAdapter.notifyDataSetChanged();
                        }
                        break;
                    case 3:  //有必要去判断吗
                        Toast.makeText(getApplicationContext(), "蓝牙扫描结束。", Toast.LENGTH_SHORT).show();
                        bluetooth_state.setText("蓝牙扫描结束。");
                        break;
                }

            super.handleMessage(msg);
        }
    };

    class MyBluetoothAdapter extends BaseAdapter {
        public List<String > lists ;
        public  Context ctx;
        public MyBluetoothAdapter(){}
        public MyBluetoothAdapter(Context ctx,List<String> lists){
            this.lists = lists;
            this.ctx = ctx;
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
                vh = new ViewHolder();
                convertView = LayoutInflater.from(ctx).inflate(R.layout.bluetoothitem,null);
                vh.textView = (TextView) convertView.findViewById(R.id.bluetooth_name);
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




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Intent intent = new Intent();
        intent.putExtra("str", Contents.ERROR);
        setResult(Contents.BLUETOOTH_NUM, intent);
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
                    setResult(Contents.BLUETOOTH_NUM, intent);
                    finish();
                    break;
                case R.id.button_error:
                    intent.putExtra("str", Contents.ERROR);
                    setResult(Contents.BLUETOOTH_NUM, intent);
                    finish();
                    break;
            }
        }else if(BaseApp.autotest == 1){
            SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (v.getId()){
                case R.id.button_right:
                    editor.putString("BLUETOOTH", "1");
                    editor.apply();
                    intent.setClass(BluetoothActivity.this,WifiActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_error:
                    editor.putString("BLUETOOTH", "0");
                    editor.apply();
                    intent.setClass(BluetoothActivity.this, WifiActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.button_replay:
                    intent.setClass(BluetoothActivity.this,BluetoothActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }

    }
}
