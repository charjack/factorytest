package com.charjack.factorytest.SecondOpt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.charjack.factorytest.R;
import com.charjack.factorytest.ThirdOpt.VersonActivity;
import com.charjack.factorytest.Utils.BaseApp;

public class AutoTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置成全屏模式
        setContentView(R.layout.activity_auto_test);
        

        Dialog alertDialog = new AlertDialog.Builder(this).
                setTitle("注意").
                setMessage("测试完成后，在按下正确或错误按钮前，可以点击replay键重测！").
              setNegativeButton("取消", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      finish();
                  }
              }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseApp.autotest = 1;
                Toast.makeText(getApplicationContext(), "您点击了确定按钮"+BaseApp.autotest, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(AutoTestActivity.this, VersonActivity.class);
                startActivity(intent);
                finish();
            }
        }).create();
        alertDialog.show();
    }

}
