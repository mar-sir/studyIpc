package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/12.
 */
public class PaintViewFour extends View {
    Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint txtPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    Context context;
    Bitmap bitmap;
    ColorMatrix colorMatrix;
    ColorMatrix colorMatrix1;
    ColorMatrix colorMatrix2;

    public PaintViewFour(Context context) {
        super(context);
        this.context=context;
        init();
    }

    public PaintViewFour(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    public PaintViewFour(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        txtPaint.setTextSize(40);
        //txtPaint.setLetterSpacing(5);
        txtPaint.setStyle(Paint.Style.STROKE);
        txtPaint.setColor(Color.BLACK);
        bitmap= BitmapFactory.decodeResource(context.getResources(), R.mipmap.sight_image);

        colorMatrix=new ColorMatrix(new float[]{
                2.0f,0,0,0,0,
                0,1.2f,0,0,0,
                0,0,0.9f,0,0,
                0,0,0,0.5f,0,
        });

        colorMatrix1=new ColorMatrix(new float[]{
                2.0f,2.0f,2.0f,0,-1,
                2.0f,2.0f,2.0f,0,-1,
                2.0f,2.0f,2.0f,0,-1,
                2.0f,2.0f,2.0f,0,-1,
        });

        colorMatrix2 = new ColorMatrix(new float[]{
                0.393F, 0.769F, 0.189F, 0, 0,
                0.349F, 0.686F, 0.168F, 0, 0,
                0.272F, 0.534F, 0.131F, 0, 0,
                0, 0, 0, 1, 0,
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("原始图片",0,20,paint);
        canvas.drawBitmap(bitmap,getWidth()/2-bitmap.getWidth()/2,getTop()+10,paint);

        canvas.drawText("改变ColorMatrix的RGB值",0,bitmap.getHeight()+40,txtPaint);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap,getWidth()/2-bitmap.getWidth()/2,bitmap.getHeight()+40,paint);

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix1));
        canvas.drawBitmap(bitmap,getWidth()/2-bitmap.getWidth()/2,bitmap.getHeight()*2+40,paint);

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        canvas.drawBitmap(bitmap,getWidth()/2-bitmap.getWidth()/2,bitmap.getHeight()*3+40,paint);

    }
}
