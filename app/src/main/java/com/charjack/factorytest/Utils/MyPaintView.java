package com.charjack.factorytest.Utils;

/**
 * Created by Administrator on 2016/4/18.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyPaintView extends View {
    OnCoverListener mCoverListener;
    private List<Point> allPoints=new ArrayList<Point>();
    public MyPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCoverListener = (OnCoverListener)context;
        super.setOnTouchListener(new OnTouchListenerImp());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p=new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);
        p.setAntiAlias(true);
        p.setColor(Color.BLUE);
        if(MyPaintView.this.allPoints.size()>1){
            Iterator<Point> iter=MyPaintView.this.allPoints.iterator();
            Point first=null;
            Point last=null;
            while(iter.hasNext()){
                if(first==null){
                    first=(Point)iter.next();
                }else{
                    if(last!=null){
                        first=last;
                    }
                    last=(Point)iter.next();
                    if(last.x == 1&& last.y == 1){

                    }else if(first.x == 1 && first.y ==1) {

                    }
                    else{
                        canvas.drawLine(first.x, first.y, last.x, last.y, p);

                        mCoverListener.onCover(first);// interface callback
                    }
                }
            }
        }
    }

    public interface OnCoverListener{
        void onCover(Point p);
    }

    private class OnTouchListenerImp implements OnTouchListener{

        public boolean onTouch(View v, MotionEvent event) {
            Point p=new Point((int)event.getX(),(int)event.getY());
            if(event.getAction()==MotionEvent.ACTION_DOWN){
            //    MyPaintView.this.allPoints=new ArrayList<Point>();
                MyPaintView.this.allPoints.add(p);
            }
            else if(event.getAction()==MotionEvent.ACTION_UP){
                MyPaintView.this.allPoints.add(p);
                MyPaintView.this.allPoints.add(new Point(1,1));
                MyPaintView.this.postInvalidate();
            }
            else if(event.getAction()==MotionEvent.ACTION_MOVE){
                MyPaintView.this.allPoints.add(p);
                MyPaintView.this.postInvalidate();
            }
            return true;
        }
    }
}
