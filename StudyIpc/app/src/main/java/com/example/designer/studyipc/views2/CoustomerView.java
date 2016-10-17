package com.example.designer.studyipc.views2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangcl on 2016/10/17.
 *
 *
 */
public class CoustomerView extends View {
    private static final String TAG = CoustomerView.class.getSimpleName();

    public CoustomerView(Context context) {
        super(context);
        init();
    }

    public CoustomerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoustomerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Log.e(TAG, TAG + "宽------>" + getWidth() + "高------>" + getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, TAG + "------>onMeasure()");
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, TAG + "------>onSizeChanged()");
        Log.e(TAG, TAG + "宽------>" + getWidth() + "高------>" + getHeight());
        Log.e(TAG, TAG + "原宽------>" + oldw + "原高------>" + oldh);
        Log.e(TAG, TAG + "宽------>" + w + "高------>" + h);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, TAG + "------>onLayout()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, TAG + "------>onDraw()");
    }
}
