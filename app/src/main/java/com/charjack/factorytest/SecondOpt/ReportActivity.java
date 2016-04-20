package com.charjack.factorytest.SecondOpt;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.charjack.factorytest.R;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends ActionBarActivity {

    ListView report_list;
    List<String> lists = new ArrayList<>();
    MyReportAdapter myReportAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_report);
        report_list = (ListView) findViewById(R.id.report_list);

        SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
        lists.add("1.版本:                "+sharedPreferences.getString("VERSON", "null"));
        lists.add("2.背光:                "+sharedPreferences.getString("BACKGROUND", "null"));
        lists.add("3.LCD :                "+sharedPreferences.getString("LCD", "null"));
        lists.add("4.按键:                "+sharedPreferences.getString("BUTTON", "null"));
        lists.add("5.麦克喇叭:        "+sharedPreferences.getString("RECORDPLAY", "null"));
        lists.add("6.摄像头:            "+sharedPreferences.getString("CAMERA", "null"));
        lists.add("7.触摸屏:            "+sharedPreferences.getString("TOUCH", "null"));
        lists.add("8.重力传感器:    "+sharedPreferences.getString("SENSOR", "null"));
        lists.add("9.蓝牙:                "+sharedPreferences.getString("BLUETOOTH", "null"));
        lists.add("10.Wifi:               "+sharedPreferences.getString("WIFI", "null"));
        lists.add("11.GPS:              "+sharedPreferences.getString("GPS", "null"));
        lists.add("12.SD卡:             "+sharedPreferences.getString("SDCARD", "null"));
        lists.add("13.SIM卡:           "+sharedPreferences.getString("SIM", "null"));

        myReportAdapter = new MyReportAdapter(this,lists);
        report_list.setAdapter(myReportAdapter);
    }
    class MyReportAdapter extends BaseAdapter {
        public List<String > lists ;
        public Context ctx;
        public MyReportAdapter(){}
        public MyReportAdapter(Context ctx,List<String> lists){
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
                convertView = LayoutInflater.from(ctx).inflate(R.layout.report_list_item,null);
                vh.textView = (TextView) convertView.findViewById(R.id.report_item);
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
