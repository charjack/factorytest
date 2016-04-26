package com.charjack.factorytest.Utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class DrawImageView extends ImageView {
    public static final String TAG = "pdi" ;
	public static boolean D = false;
    public final Paint paint;
    public final Context mContext;
    public RectF mRectF;

    public int RDATA_DISPLAY_LEFT_LEVEL= -1;
    public int RDATA_DISPLAY_RIGHT_LEVEL= -1;
    public int RDATA_DISPLAY_MIDDLE_LEVEL= -1;


    public DrawImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
    }

	@Override
    public void onDraw(Canvas canvas) {

       if(D) Log.v(TAG, "=======RDATA_DISPLAY_RIGHT_LEVEL=" + RDATA_DISPLAY_RIGHT_LEVEL + "RDATA_DISPLAY_LEFT_LEVEL" + RDATA_DISPLAY_LEFT_LEVEL + "RDATA_DISPLAY_MIDDLE_LEVEL" + RDATA_DISPLAY_MIDDLE_LEVEL);

        switch(RDATA_DISPLAY_RIGHT_LEVEL) {
            case 421:
                Right_1_Draw(canvas);
            case 422:
                Right_2_Draw(canvas);
            case 423:
                Right_3_Draw(canvas);
            default:

        }
        switch (RDATA_DISPLAY_LEFT_LEVEL){
            case 301:
                Left_1_Draw(canvas);
            case 302:
                Left_2_Draw(canvas);
            case 303:
                Left_3_Draw(canvas);
            default:

        }
        switch(RDATA_DISPLAY_MIDDLE_LEVEL){
            case 311:
                Middle_1_Draw(canvas);
            case 312:
                Middle_2_Draw(canvas);
            case 313:
                Middle_3_Draw(canvas);
            case 314:
                Middle_4_Draw(canvas);
            default :
        }

        super.onDraw(canvas);
  }

    public void Left_3_Draw(Canvas canvas){
        // Left_3
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.argb(255, 153, 232, 0));
        int center = getWidth()/2 + 8 ;
        int radius = 135 ;
        float startAngle_First = 100;
        float sweepAngle01_First = 9;

       // paint.setColor(Color.argb(255, 153, 232, 0));
        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_First, sweepAngle01_First, false, paint);
    }

    public void Left_2_Draw(Canvas canvas){
        // Left_2
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.YELLOW);
        int center = getWidth()/2 + 8;
        int radius = 123 ;
        float startAngle_First = 100;
        float sweepAngle01_First = 7;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_First, sweepAngle01_First, false, paint);
    }

    public void Left_1_Draw(Canvas canvas){
        // Left_2
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.RED);
        int center = getWidth()/2 + 8 ;
        int radius = 111 ;
        float startAngle_First = 101;
        float sweepAngle01_First = 5;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_First, sweepAngle01_First, false, paint);
    }

    public void Right_3_Draw(Canvas canvas){
        // Right_3
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.argb(255, 153, 232, 0));
        int center = getWidth()/2 -12 ;
        int radius = 155 ;
        float startAngle_First = 70;
        float sweepAngle01_First = 8 ;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_First, sweepAngle01_First, false, paint);
    }

    public void Right_2_Draw(Canvas canvas){
        // Right_2
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.YELLOW);
        int center = getWidth()/2 -12  ;
        int radius = 143 ;
        float startAngle_First = 71;
        float sweepAngle01_First = 7;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_First, sweepAngle01_First, false, paint);
    }

    public void Right_1_Draw(Canvas canvas){
        // Right_1
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.RED);
        int  center = getWidth()/2 - 12 ;
        int  radius = 131 ;
        float startAngle_First = 72;
        float sweepAngle01_First = 5;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_First, sweepAngle01_First, false, paint);
    }


    public void Middle_1_Draw(Canvas canvas){
        // Middle_1
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.RED);
        int center = getWidth()/2 ;
        int radius = 119 ;
        float startAngle_Mid = 86;
        float sweepAngle_Mid = 6;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_Mid, sweepAngle_Mid, false, paint);

    }

    public void Middle_2_Draw(Canvas canvas){
        //Middle_2
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.YELLOW);
        int center = getWidth()/2 ;
        int radius = 131 ;
        float startAngle_Mid = 85;
        float sweepAngle_Mid = 8;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_Mid, sweepAngle_Mid, false, paint);
    }

    public void Middle_3_Draw(Canvas canvas){
        //Middle_3
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.argb(255, 153, 232, 0));
        int center = getWidth()/2 ;
        int radius = 143 ;
        float startAngle_Mid = 84;
        float sweepAngle_Mid = 10;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_Mid, sweepAngle_Mid, false, paint);
    }

    public void Middle_4_Draw(Canvas canvas){
        //Middle_4
        paint.setStrokeWidth(7);
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.GREEN);
        int center = getWidth()/2 ;
        int radius = 155 ;
        float startAngle_Mid = 83;
        float sweepAngle_Mid = 12;

        mRectF = new RectF(center - radius, center - radius, center
                + radius, center + radius);
        canvas.drawArc(mRectF, startAngle_Mid, sweepAngle_Mid, false, paint);
    }

    public void  setDisplay(boolean isDisplay){
       if(D) Log.d(TAG, "setDisplay");

      if(isDisplay) {
         // postInvalidate();
         new Thread(new RdThread()).start();
      }
    }

	class RdThread implements Runnable {
		public void run(){
			while (!Thread.currentThread().isInterrupted()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e){
				Thread.currentThread().interrupt();
			}
			postInvalidate(); 
			} 
		} 
	}

	

    public void parseRData(byte[] bytes){
        String sData_0 = Integer.toHexString(bytes[0] & 0x000000ff);
        String sData_1 = Integer.toHexString(bytes[1] & 0x000000ff);
        String sData_2 = Integer.toHexString(bytes[2] & 0x000000ff);
        String sData_3 = Integer.toHexString(bytes[3] & 0x000000ff);
        String sData_4 = Integer.toHexString(bytes[4] & 0x000000ff);
        String sData_5 = Integer.toHexString(bytes[5] & 0x000000ff);
        String sData_6 = Integer.toHexString(bytes[6] & 0x000000ff);
        String sData_7 = Integer.toHexString(bytes[7] & 0x000000ff);

        int iData_0 = (bytes[0] & 0x000000ff);
        int iData_1 = (bytes[1] & 0x000000ff);
        int iData_2 = (bytes[2] & 0x000000ff);
        int iData_3 = (bytes[3] & 0x000000ff);
        int iData_4 = (bytes[4] & 0x000000ff);
        int iData_5 = (bytes[5] & 0x000000ff);
        int iData_6 = (bytes[6] & 0x000000ff);
        int iData_7 = (bytes[7] & 0x000000ff);

        if( sData_0 == Integer.toHexString(0x98) ){
            if(D) Log.d(TAG, "Have voice alerts");
         }else{
            if(D) Log.d(TAG, "No voice alerts");
        }

        if( sData_5 == Integer.toHexString(0xfe)){
            if(D) Log.d(TAG, "Display rada picture");
        }else{
           if(D) Log.d(TAG, "No Display rada picture");
        }
// Key Value
// xxx iData_Left/Middle/Right(0/1/2)_display(4/3/2/1/0)
//
        if ((iData_4 >= 20)&&(iData_4<=31)){
           if(D) Log.d(TAG, "back_right_display3");
            RDATA_DISPLAY_RIGHT_LEVEL = 423 ;
        }else if ((iData_4>=52) && (iData_4<=60)){
           if(D) Log.d(TAG, "back_right_display2");
            RDATA_DISPLAY_RIGHT_LEVEL = 422 ;
        }else if((iData_4>=84) && (iData_4<=92)){
           if(D) Log.d(TAG, "back_right_display1");
            RDATA_DISPLAY_RIGHT_LEVEL = 421 ;
        }else{
            if(D) Log.d(TAG, "no display back_right");
        }

        if((iData_3>=0)  && (iData_3<=3) )
        {
            if(D) Log.d(TAG, "display back_Left_display3");
            RDATA_DISPLAY_LEFT_LEVEL = 303;
        }else if((iData_3>=32)  && (iData_3<=63) )
        {
            if(D) Log.d(TAG, "display back_Left_display2");
            RDATA_DISPLAY_LEFT_LEVEL = 302;
        }else if((iData_3>=64)  && (iData_3<=95) )
        {
            if(D) Log.d(TAG, "display back_Left_display1");
            RDATA_DISPLAY_LEFT_LEVEL = 301;
        }else if((iData_3>=96)  && (iData_3<=127) )
        {
            if(D) Log.d(TAG, "no display back_Left_display");
            RDATA_DISPLAY_LEFT_LEVEL = 300;
        }else{
            if(D) Log.d(TAG, "data3 is error");
        }

        if(((iData_3>=0)&&(iData_3<=3)) || ((iData_3>=32)&&(iData_3<=35)) || ((iData_3>=64)&&(iData_3<=67)) ||((iData_3>=96)&&(iData_3<=99)))
        {
            if(D) Log.d(TAG, "display back_Middle_display4");
            RDATA_DISPLAY_MIDDLE_LEVEL = 314;
        }else  if(((iData_3>=4)&&(iData_3<=11)) || ((iData_3>=36)&&(iData_3<=43)) || ((iData_3>=68)&&(iData_3<=75)) ||((iData_3>=100)&&(iData_3<=107)))
        {
            if(D) Log.d(TAG, "display back_Middle_display3");
            RDATA_DISPLAY_MIDDLE_LEVEL = 313;
        }else  if(((iData_3>=12)&&(iData_3<=19)) || ((iData_3>=44)&&(iData_3<=51)) || ((iData_3>=76)&&(iData_3<=83)) ||((iData_3>=108)&&(iData_3<=115)))
        {
            if(D) Log.d(TAG, "display back_Middle_display2");
            RDATA_DISPLAY_MIDDLE_LEVEL = 312;
        }else  if(((iData_3>=20)&&(iData_3<=27)) || ((iData_3>=52)&&(iData_3<=59)) || ((iData_3>=84)&&(iData_3<=91)) ||((iData_3>=116)&&(iData_3<=123)))
        {
            if(D) Log.v(TAG, "display back_Middle_display1");
            RDATA_DISPLAY_MIDDLE_LEVEL = 311;
        }else  if(((iData_3>=28)&&(iData_3<=31)) || ((iData_3>=60)&&(iData_3<=63)) || ((iData_3>=92)&&(iData_3<=95)) ||((iData_3>=124)&&(iData_3<=127)))
        {
            if(D) Log.d(TAG, "no display back_Middle_display");
            RDATA_DISPLAY_MIDDLE_LEVEL = 310;
        }else{
            if(D) Log.d(TAG, "data3 is error");
        }

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;    
        return (int) (dpValue * scale + 0.5f);    
    }    
}
