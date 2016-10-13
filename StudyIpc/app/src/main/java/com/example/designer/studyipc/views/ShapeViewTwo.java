package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/13.
 */
public class ShapeViewTwo extends View {
    private Paint fillPaint=new Paint(Paint.ANTI_ALIAS_FLAG);//tianchong画笔
    private Paint strokPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private BitmapShader bitmapShader;//BitBap着色器
    private Bitmap bitmap;

    private float posX,posY;//触点坐标


    public ShapeViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeViewTwo(Context context) {
        super(context);
        init();
    }

    public ShapeViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
      strokPaint.setColor(0xff000000);
        strokPaint.setStyle(Paint.Style.STROKE);
        strokPaint.setStrokeWidth(5);

        bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.foot_image);
        bitmapShader=new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        fillPaint.setShader(bitmapShader);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            posX=event.getX();
            posY=event.getY();
            //同步绘制
            invalidate();
        }
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画笔背景色
        canvas.drawColor(Color.RED);

        /*
         * 绘制圆和描边
         */
        canvas.drawCircle(posX, posY, 100, fillPaint);
        canvas.drawCircle(posX, posY, 100, strokPaint);
    }
}
