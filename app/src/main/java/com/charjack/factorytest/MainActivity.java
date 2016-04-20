package com.charjack.factorytest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.charjack.factorytest.SecondOpt.AutoTestActivity;
import com.charjack.factorytest.SecondOpt.ManuActivity;
import com.charjack.factorytest.SecondOpt.ReportActivity;
import com.charjack.factorytest.Utils.BaseApp;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public ListView main_list;
    public List<String> mLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//强制为横屏
//怎么github同步的时候，没有全部同步
        setContentView(R.layout.activity_main);
        main_list = (ListView) findViewById(R.id.main_list);
        initdata();
        MyAdapter myAdapter = new MyAdapter(this, mLists);
        main_list.setAdapter(myAdapter);
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(BaseApp.ifdebug)
                    Toast.makeText(getApplicationContext(),"click"+mLists.get(position),Toast.LENGTH_SHORT).show();

                Intent intent  = new Intent();
                switch(position){
                    case 0: //自动测试 //显示dialog
                        intent.setClass(MainActivity.this,AutoTestActivity.class);
                        startActivity(intent);
                        break;
                    case 1: //手动测试
                        intent.setClass(MainActivity.this,ManuActivity.class);
                        startActivity(intent);
                        break;
                    case 2: //测试报告
                        intent.setClass(MainActivity.this,ReportActivity.class);
                        startActivity(intent);
                        break;
                    case 3: //修改开机动画
                        break;
                    case 4: //修改APN
                        break;
                    case 5: //修改IP地址
                        break;
                    default:
                        break;
                }


            }
        });
    }

    private void initdata() {
        mLists.add("自动测试");
        mLists.add("手动测试");
        mLists.add("测试报告");
        mLists.add("修改开机动画");
        mLists.add("修改APN");
        mLists.add("修改IP地址");
    }

    class MyAdapter extends BaseAdapter{
        public List<String > lists ;
        public  Context ctx;
        public MyAdapter(){}
        public MyAdapter(Context ctx,List<String> lists){
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
                convertView = LayoutInflater.from(ctx).inflate(R.layout.main_list_item,null);
                vh.textView = (TextView) convertView.findViewById(R.id.main_list_item);
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
