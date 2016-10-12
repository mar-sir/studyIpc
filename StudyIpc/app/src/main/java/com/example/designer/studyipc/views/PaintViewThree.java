package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangcl on 2016/10/12.
 * 介绍setColorFilter（ColorFilter filter）
 */
public class PaintViewThree extends View {
    //  SETCOLORFILTE(7,"ColrFilter setColorFilter(ColorFilter filter),设置颜色过滤，
    // ColorFilter有三个子类去实现ColorMatrixColorFilter、LightingColorFilter和PorterDuffColorFilter"),
    /**
     *   ColorMatrix colorMatrix=new ColorMatrix(new float[]{
     *                                           1(r),0,0,0,0,//R
     *                                           0,1(g),0,0,0,//G
     *                                           0,0,1(b),0,0,//B
     *                                           0,0,0,1(alpha),0(偏移量),//alpha
     *                                           });//取值范围0.0f-2.2f之间,1为保持原图的RGB值.
     *每一行的第五列数字表示偏移值，何为偏移值？顾名思义当我们想让颜色更倾向于红色的时候就增大R向量中的偏移值，
     * 想让颜色更倾向于蓝色的时候就增大B向量中的偏移值
     */
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint txtPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Context context;
    public PaintViewThree(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public PaintViewThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public PaintViewThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        //设置自定义颜色
        paint.setColor(Color.argb(255,32,213,23));
        //设置画笔粗细
        paint.setStrokeWidth(5);
        ColorMatrix colormatrix=new ColorMatrix(new float[]{
                1,0,0,0,0,
                0,1,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        });
       paint.setColorFilter(new ColorMatrixColorFilter(colormatrix));

        txtPaint.setColor(Color.WHITE);
        txtPaint.setTextSize(18);


    }


    //会调用很多次
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawText("ColorMatrix----->",0,100,txtPaint);
        canvas.drawText("RGBA都为1，图形没变化",0,200,txtPaint);
        canvas.drawCircle(getWidth()/2,getHeight()/4+10,200,paint);

        canvas.drawText("RGBA变化",0,getHeight()/4+10+200,txtPaint);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                1.5F, 0, 0, 0, 0,
                0, 0.5F, 0, 0, 0,
                0, 0, 2.0F, 0, 0,
                0, 0, 0, 1, 1,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawCircle(getWidth()/2,getHeight()/4+10+200,200,paint);

    }
}
