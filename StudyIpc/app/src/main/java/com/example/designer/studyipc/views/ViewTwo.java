package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangcl on 2016/10/11.
 */
public class ViewTwo extends View implements Runnable{
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private int R=100;

    public ViewTwo(Context context) {
        super(context);
        initView();
    }

    public ViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }

    @Override
    public void run() {

    }
}
