package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/13.
 * setShader(Shader shader)    setShader(21,"Shader  setShader(Shader shader),
 * 设置着色器，Shader 子类实现有BitmapShader, ComposeShader, LinearGradient, RadialGradient, SweepGradient");
 */
public class ShapeViewOne extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int centerX, centerY;
    private int left, top, right, bootom;//矩形坐上右下角坐标
    private Bitmap bitmap;


    public ShapeViewOne(Context context) {
        super(context);
        init();
    }

    public ShapeViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //获取位图
         bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.image_s);

        //设置着色器

      //  paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP));

       // paint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));

        //paint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY=getHeight()/2;
        left = centerX - bitmap.getWidth()/2;
        top = centerY -bitmap.getHeight()/2;
        right = centerX + bitmap.getWidth()/2;
        bootom = centerY + bitmap.getHeight()/2;

        canvas.drawRect(left,top,right,bootom,paint);
    }
}
