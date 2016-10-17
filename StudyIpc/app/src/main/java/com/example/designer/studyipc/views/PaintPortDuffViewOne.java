package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangcl on 2016/10/14.
 * PorterDuffer模式常量
 */
public class PaintPortDuffViewOne extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final PorterDuff.Mode MODE = PorterDuff.Mode.ADD;

    private static final int RECT_SIZE_SMALL=400;//左右上方示例渐变正方形的尺寸大小

    private static final int RECT_SIZE_BIG=800;//中间测试渐变正方形的尺寸大小



    public PaintPortDuffViewOne(Context context) {
        super(context);
        init();
    }

    public PaintPortDuffViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintPortDuffViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


}
