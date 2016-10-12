package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangcl on 2016/10/11.
 */
public class PaintViewTwo extends View implements Runnable {


    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int radiu = 200;

    public PaintViewTwo(Context context) {
        super(context);
        initView();
    }

    public PaintViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PaintViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radiu, paint);
    }

    @Override
    public void run() {
        while (true) {
            try {
                /*
                 * 如果半径小于200则自加否则大于200后重置半径值以实现往复
                 */
                if (radiu <= 20) {
                    radiu = 200;
                } else {
                    radiu -= 20;
                    // 刷新View//
                    //invalidate();同步刷新
                    postInvalidate();
                }
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
