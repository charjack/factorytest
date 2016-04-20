package com.charjack.factorytest.ThirdOpt;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.charjack.factorytest.Contents;
import com.charjack.factorytest.R;
import com.charjack.factorytest.Utils.BaseApp;
import com.charjack.factorytest.Utils.MyPaintView;

public class TouchActivity extends Activity implements MyPaintView.OnCoverListener,View.OnClickListener {

    public static Button button11,button12,button13,button14,button15,button16,button17,button18,button19;
    public static Button button21,button29;
    public static Button button31,button32,button33,button34,button35,button36,button37,button38,button39;
    public static Button button41,button49;
    public static Button button51,button52,button53,button54,button55,button56,button57,button58,button59;
    public static Button button61,button69;
    public static Button button71,button72,button73,button74,button75,button76,button77,button78,button79;
    public static Button button81,button89;
    public static Button button91,button92,button93,button94,button95,button96,button97,button98,button99;
    public static Button button101,button109;
    public static Button button111,button112,button113,button114,button115,button116,button117,button118,button119;
    public static Button button121,button129;
    public static Button button131,button132,button133,button134,button135,button136,button137,button138,button139;
    public static short[] var={
            0,0,0,0,0,0,0,0,0,
            0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,
            0,0,0,0,0,0,0,0,0,
    };
    public int screenWidth,screenHeigh;
    public int rednum = 0;
    public Button button_right;

    public static void setbackgroud(int num){
        switch(num){
            case 11:{button11.setBackgroundResource(R.drawable.buttonstyle3);var[0] = 1;break;}
            case 12:{button12.setBackgroundResource(R.drawable.buttonstyle3);var[1] = 1;break;}
            case 13:{button13.setBackgroundResource(R.drawable.buttonstyle3);var[2] = 1;break;}
            case 14:{button14.setBackgroundResource(R.drawable.buttonstyle3);var[3] = 1;break;}
            case 15:{button15.setBackgroundResource(R.drawable.buttonstyle3);var[4] = 1;break;}
            case 16:{button16.setBackgroundResource(R.drawable.buttonstyle3);var[5] = 1;break;}
            case 17:{button17.setBackgroundResource(R.drawable.buttonstyle3);var[6] = 1;break;}
            case 18:{button18.setBackgroundResource(R.drawable.buttonstyle3);var[7] = 1;break;}
            case 19:{button19.setBackgroundResource(R.drawable.buttonstyle3);var[8] = 1;break;}

            case 21:{button21.setBackgroundResource(R.drawable.buttonstyle3);var[9] = 1;break;}
            case 29:{button29.setBackgroundResource(R.drawable.buttonstyle3);var[10] = 1;break;}

            case 31:{button31.setBackgroundResource(R.drawable.buttonstyle3);var[11] = 1;break;}
            case 32:{button32.setBackgroundResource(R.drawable.buttonstyle3);var[12] = 1;break;}
            case 33:{button33.setBackgroundResource(R.drawable.buttonstyle3);var[13] = 1;break;}
            case 34:{button34.setBackgroundResource(R.drawable.buttonstyle3);var[14] = 1;break;}
            case 35:{button35.setBackgroundResource(R.drawable.buttonstyle3);var[15] = 1;break;}
            case 36:{button36.setBackgroundResource(R.drawable.buttonstyle3);var[16] = 1;break;}
            case 37:{button37.setBackgroundResource(R.drawable.buttonstyle3);var[17] = 1;break;}
            case 38:{button38.setBackgroundResource(R.drawable.buttonstyle3);var[18] = 1;break;}
            case 39:{button39.setBackgroundResource(R.drawable.buttonstyle3);var[19] = 1;break;}

            case 41:{button41.setBackgroundResource(R.drawable.buttonstyle3);var[20] = 1;break;}
            case 49:{button49.setBackgroundResource(R.drawable.buttonstyle3);var[21] = 1;break;}

            case 51:{button51.setBackgroundResource(R.drawable.buttonstyle3);var[22] = 1;break;}
            case 52:{button52.setBackgroundResource(R.drawable.buttonstyle3);var[23] = 1;break;}
            case 53:{button53.setBackgroundResource(R.drawable.buttonstyle3);var[24] = 1;break;}
            case 54:{button54.setBackgroundResource(R.drawable.buttonstyle3);var[25] = 1;break;}
            case 55:{button55.setBackgroundResource(R.drawable.buttonstyle3);var[26] = 1;break;}
            case 56:{button56.setBackgroundResource(R.drawable.buttonstyle3);var[27] = 1;break;}
            case 57:{button57.setBackgroundResource(R.drawable.buttonstyle3);var[28] = 1;break;}
            case 58:{button58.setBackgroundResource(R.drawable.buttonstyle3);var[29] = 1;break;}
            case 59:{button59.setBackgroundResource(R.drawable.buttonstyle3);var[30] = 1;break;}

            case 61:{button61.setBackgroundResource(R.drawable.buttonstyle3);var[31] = 1;break;}
            case 69:{button69.setBackgroundResource(R.drawable.buttonstyle3);var[32] = 1;break;}

            case 71:{button71.setBackgroundResource(R.drawable.buttonstyle3);var[33] = 1;break;}
            case 72:{button72.setBackgroundResource(R.drawable.buttonstyle3);var[34] = 1;break;}
            case 73:{button73.setBackgroundResource(R.drawable.buttonstyle3);var[35] = 1;break;}
            case 74:{button74.setBackgroundResource(R.drawable.buttonstyle3);var[36] = 1;break;}
            case 75:{button75.setBackgroundResource(R.drawable.buttonstyle3);var[37] = 1;break;}
            case 76:{button76.setBackgroundResource(R.drawable.buttonstyle3);var[38] = 1;break;}
            case 77:{button77.setBackgroundResource(R.drawable.buttonstyle3);var[39] = 1;break;}
            case 78:{button78.setBackgroundResource(R.drawable.buttonstyle3);var[40] = 1;break;}
            case 79:{button79.setBackgroundResource(R.drawable.buttonstyle3);var[41] = 1;break;}

            case 81:{button81.setBackgroundResource(R.drawable.buttonstyle3);var[42] = 1;break;}
            case 89:{button89.setBackgroundResource(R.drawable.buttonstyle3);var[43] = 1;break;}

            case 91:{button91.setBackgroundResource(R.drawable.buttonstyle3);var[44] = 1;break;}
            case 92:{button92.setBackgroundResource(R.drawable.buttonstyle3);var[45] = 1;break;}
            case 93:{button93.setBackgroundResource(R.drawable.buttonstyle3);var[46] = 1;break;}
            case 94:{button94.setBackgroundResource(R.drawable.buttonstyle3);var[47] = 1;break;}
            case 95:{button95.setBackgroundResource(R.drawable.buttonstyle3);var[48] = 1;break;}
            case 96:{button96.setBackgroundResource(R.drawable.buttonstyle3);var[49] = 1;break;}
            case 97:{button97.setBackgroundResource(R.drawable.buttonstyle3);var[50] = 1;break;}
            case 98:{button98.setBackgroundResource(R.drawable.buttonstyle3);var[51] = 1;break;}
            case 99:{button99.setBackgroundResource(R.drawable.buttonstyle3);var[52] = 1;break;}

            case 101:{button101.setBackgroundResource(R.drawable.buttonstyle3);var[53] = 1;break;}
            case 109:{button109.setBackgroundResource(R.drawable.buttonstyle3);var[54] = 1;break;}

            case 111:{button111.setBackgroundResource(R.drawable.buttonstyle3);var[55] = 1;break;}
            case 112:{button112.setBackgroundResource(R.drawable.buttonstyle3);var[56] = 1;break;}
            case 113:{button113.setBackgroundResource(R.drawable.buttonstyle3);var[57] = 1;break;}
            case 114:{button114.setBackgroundResource(R.drawable.buttonstyle3);var[58] = 1;break;}
            case 115:{button115.setBackgroundResource(R.drawable.buttonstyle3);var[59] = 1;break;}
            case 116:{button116.setBackgroundResource(R.drawable.buttonstyle3);var[60] = 1;break;}
            case 117:{button117.setBackgroundResource(R.drawable.buttonstyle3);var[61] = 1;break;}
            case 118:{button118.setBackgroundResource(R.drawable.buttonstyle3);var[62] = 1;break;}
            case 119:{button119.setBackgroundResource(R.drawable.buttonstyle3);var[63] = 1;break;}

            case 121:{button121.setBackgroundResource(R.drawable.buttonstyle3);var[64] = 1;break;}
            case 129:{button129.setBackgroundResource(R.drawable.buttonstyle3);var[65] = 1;break;}

            case 131:{button131.setBackgroundResource(R.drawable.buttonstyle3);var[66] = 1;break;}
            case 132:{button132.setBackgroundResource(R.drawable.buttonstyle3);var[67] = 1;break;}
            case 133:{button133.setBackgroundResource(R.drawable.buttonstyle3);var[68] = 1;break;}
            case 134:{button134.setBackgroundResource(R.drawable.buttonstyle3);var[69] = 1;break;}
            case 135:{button135.setBackgroundResource(R.drawable.buttonstyle3);var[70] = 1;break;}
            case 136:{button136.setBackgroundResource(R.drawable.buttonstyle3);var[71] = 1;break;}
            case 137:{button137.setBackgroundResource(R.drawable.buttonstyle3);var[72] = 1;break;}
            case 138:{button138.setBackgroundResource(R.drawable.buttonstyle3);var[73] = 1;break;}
            case 139:{button139.setBackgroundResource(R.drawable.buttonstyle3);var[74] = 1;break;}
            default:break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_touch);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeigh = dm.heightPixels;
        System.out.println("screenWidth : "+"screenHeigh"+"---"+screenWidth/9+" : "+screenHeigh/13);
        for(int i=0;i<75;i++){
            var[i] = 0 ;
        }
        button_right = (Button) findViewById(R.id.button_right);
        button_right.setOnClickListener(this);
        rednum = 0;
        button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button13 = (Button) findViewById(R.id.button13);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        button17 = (Button) findViewById(R.id.button17);
        button18 = (Button) findViewById(R.id.button18);
        button19 = (Button) findViewById(R.id.button19);

        button21 = (Button) findViewById(R.id.button21);
        button29 = (Button) findViewById(R.id.button29);

        button31 = (Button) findViewById(R.id.button31);
        button32 = (Button) findViewById(R.id.button32);
        button33 = (Button) findViewById(R.id.button33);
        button34 = (Button) findViewById(R.id.button34);
        button35 = (Button) findViewById(R.id.button35);
        button36 = (Button) findViewById(R.id.button36);
        button37 = (Button) findViewById(R.id.button37);
        button38 = (Button) findViewById(R.id.button38);
        button39 = (Button) findViewById(R.id.button39);

        button41 = (Button) findViewById(R.id.button41);
        button49 = (Button) findViewById(R.id.button49);

        button51 = (Button) findViewById(R.id.button51);
        button52 = (Button) findViewById(R.id.button52);
        button53 = (Button) findViewById(R.id.button53);
        button54 = (Button) findViewById(R.id.button54);
        button55 = (Button) findViewById(R.id.button55);
        button56 = (Button) findViewById(R.id.button56);
        button57 = (Button) findViewById(R.id.button57);
        button58 = (Button) findViewById(R.id.button58);
        button59 = (Button) findViewById(R.id.button59);

        button61 = (Button) findViewById(R.id.button61);
        button69 = (Button) findViewById(R.id.button69);

        button71 = (Button) findViewById(R.id.button71);
        button72 = (Button) findViewById(R.id.button72);
        button73 = (Button) findViewById(R.id.button73);
        button74 = (Button) findViewById(R.id.button74);
        button75 = (Button) findViewById(R.id.button75);
        button76 = (Button) findViewById(R.id.button76);
        button77 = (Button) findViewById(R.id.button77);
        button78 = (Button) findViewById(R.id.button78);
        button79 = (Button) findViewById(R.id.button79);

        button81 = (Button) findViewById(R.id.button81);
        button89 = (Button) findViewById(R.id.button89);

        button91 = (Button) findViewById(R.id.button91);
        button92 = (Button) findViewById(R.id.button92);
        button93 = (Button) findViewById(R.id.button93);
        button94 = (Button) findViewById(R.id.button94);
        button95 = (Button) findViewById(R.id.button95);
        button96 = (Button) findViewById(R.id.button96);
        button97 = (Button) findViewById(R.id.button97);
        button98 = (Button) findViewById(R.id.button98);
        button99 = (Button) findViewById(R.id.button99);

        button101 = (Button) findViewById(R.id.button101);
        button109 = (Button) findViewById(R.id.button109);

        button111 = (Button) findViewById(R.id.button111);
        button112 = (Button) findViewById(R.id.button112);
        button113 = (Button) findViewById(R.id.button113);
        button114 = (Button) findViewById(R.id.button114);
        button115 = (Button) findViewById(R.id.button115);
        button116 = (Button) findViewById(R.id.button116);
        button117 = (Button) findViewById(R.id.button117);
        button118 = (Button) findViewById(R.id.button118);
        button119 = (Button) findViewById(R.id.button119);

        button121 = (Button) findViewById(R.id.button121);
        button129 = (Button) findViewById(R.id.button129);

        button131 = (Button) findViewById(R.id.button131);
        button132 = (Button) findViewById(R.id.button132);
        button133 = (Button) findViewById(R.id.button133);
        button134 = (Button) findViewById(R.id.button134);
        button135 = (Button) findViewById(R.id.button135);
        button136 = (Button) findViewById(R.id.button136);
        button137 = (Button) findViewById(R.id.button137);
        button138 = (Button) findViewById(R.id.button138);
        button139 = (Button) findViewById(R.id.button139);
    }

    @Override
    public void onCover(Point p) {
        //132  59
     //   System.out.println("point:" + p.x + " : " + p.y + "------");
        int num = 0;
        num = (p.x)/(screenWidth/9)+1;
        num = num+10*((p.y)/(screenHeigh/13)+1);
        setbackgroud(num);
         rednum = 0;
        for(int i=0;i<75;i++){
            rednum += var[i] ;
        }
        Intent intent = new Intent();
        if(rednum >= 75){
            if(BaseApp.autotest == 0){
                intent.putExtra("str", Contents.RIGHT);
                setResult(Contents.TOUCH_NUM, intent);
                rednum = 0;
                finish();
            }else if(BaseApp.autotest == 1){
                button_right.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = new Intent();
        SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(BaseApp.autotest == 0){
            intent.putExtra("str", Contents.ERROR);
            setResult(Contents.TOUCH_NUM, intent);
            finish();
        }else if(BaseApp.autotest == 1){
            editor.putString("TOUCH", "0");
            editor.apply();
            intent.setClass(TouchActivity.this,SensorActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        SharedPreferences sharedPreferences= getSharedPreferences("ResultSave", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (v.getId()){
            case R.id.button_right:
                editor.putString("TOUCH", "1");
                editor.apply();
                intent.setClass(TouchActivity.this,SensorActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
