package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/13.
 */
public class ShapeViewFour extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;//位图

    private PorterDuffXfermode xfermode;//图形混合模式

    private int x, y;//位图坐标

    private int screenW, screenH;//屏幕宽高
    //////////////////////////////////////////////



    public ShapeViewFour(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ShapeViewFour(Context context) {
        super(context);
        init();
    }

    public ShapeViewFour(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
      /*  WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenH = windowManager.getDefaultDisplay().getHeight();
        screenW = windowManager.getDefaultDisplay().getWidth();*/

        //获取位图
        bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.beauty_one);
        //实例化混合模式
        xfermode=new PorterDuffXfermode(PorterDuff.Mode.SCREEN);
        //初始化画笔
        //去饱和，体色，色相矫正
        ColorMatrix matrix=new ColorMatrix(new float[]{
           0.8587f,0.2940f,-0.0927f,0,6.77f,
           0.0821f,0.9145f,0.00634f,0,6.79f,
           0.2019f,0.1097f,0.7483f,0,6.79f,
           0,0,0,1,0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        screenW=getWidth();
        screenH=getHeight();
        x=screenW/2-bitmap.getWidth()/2;
        y=screenH/2-bitmap.getHeight()/2;
        canvas.drawColor(Color.BLACK);
        //设置径向渐变，渐变中心当然是图片的中心也是屏幕中心，渐变半径我们直接拿图片的高度但是要稍微小一点
        // 中心颜色为透明而边缘颜色为黑色
        paint.setShader(new RadialGradient(screenW/2,screenH/2,bitmap.getHeight()*7/8,Color.TRANSPARENT, Color.BLACK, Shader.TileMode.CLAMP));
        //新建图层
        int sc=canvas.saveLayer(x,y,x+bitmap.getHeight(),y+bitmap.getHeight(),null,Canvas.ALL_SAVE_FLAG);
        //绘制混合颜色
        canvas.drawColor(0xcc1c093e);
        //设置混合模式
        paint.setXfermode(xfermode);
        //绘制位图
        canvas.drawBitmap(bitmap,x,y,paint);
        // 还原混合模式
        paint.setXfermode(null);

        // 还原画布
        canvas.restoreToCount(sc);

        // 绘制一个跟图片大小一样的矩形
        canvas.drawRect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight(), paint);

    }
}
