package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/12.
 * <p/>
 * LightingColorFilter（你太mul,int add）
 * mul全称是colorMultiply意为色彩倍增，而add全称是colorAdd意为色彩添加，
 * 这两个值都是16进制的色彩值0xAARRGGBB。这个方法使用也是非常的简单。还是拿
 * 上面那张图片来说吧，比如我们想要去掉绿色。
 */
public class PaintViewFive extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint txtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    LightingColorFilter colorFilter;
    LightingColorFilter colorFilter1;
    LightingColorFilter colorFilter2;
    LightingColorFilter colorFilter3;

    Context context;
    Bitmap bitmap;

    public PaintViewFive(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public PaintViewFive(Context context) {
        super(context);
        this.context = context;
    }

    public PaintViewFive(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        txtPaint.setColor(Color.BLACK);
        txtPaint.setTextSize(20);
        txtPaint.setStrokeWidth(10);
        colorFilter = new LightingColorFilter(0xFFFFFFFF, 0x00000000);//此时图片不会有任何变化;
        //过滤绿色
        colorFilter1 = new LightingColorFilter(0xFFFF00FF, 0x00000000);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sight_image);
        //感觉更像是把过滤掉的颜色替换成绿色
        colorFilter2 = new LightingColorFilter(0xFFFFFF56, 0x0000ff00);

        colorFilter3 = new LightingColorFilter(0xFF5599FF, 0x00000000);

    }


    /**
     * LightingColorFilter(0xFFFFFFFF, 0x00000000)的时候原图是不会有任何改变的，
     * 如果我们想增加红色的值，那么LightingColorFilter(0xFFFFFFFF, 0x00XX0000)就好，
     * 其中XX取值为00至FF。
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //原图
        canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, bitmap.getHeight() * 3 + 20, paint);
        paint.setColorFilter(colorFilter1);
        canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, getTop(), paint);
        paint.setColorFilter(colorFilter2);
        canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, bitmap.getHeight() + 20, paint);
        paint.setColorFilter(colorFilter3);
        canvas.drawBitmap(bitmap, getWidth() / 2 - bitmap.getWidth() / 2, bitmap.getHeight() * 2 + 20, paint);
        // 如果已经被点击了则点击时设置颜色过滤为空还原本色
        //        paint.setColorFilter(null);
    }
}
