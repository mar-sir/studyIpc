package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/13.
 * <p/>
 * LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile)
 * 参数虽多其实很好理解x0和y0表示渐变的起点坐标而x1和y1则表示渐变的终点坐标，
 * 这两点都是相对于屏幕坐标系而言的，而color0和color1则表示起点的颜色和终点的颜色.
 */
public class ShapeViewThree extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint paint4=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint5=new Paint(Paint.ANTI_ALIAS_FLAG);



    private Bitmap srcBitmap,targetBitmap;
    private PorterDuffXfermode mxfermode;//混合模式

    private int x,y;//位图起点坐标


    public ShapeViewThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeViewThree(Context context) {
        super(context);
        init();
    }

    public ShapeViewThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setStrokeWidth(5);
        paint.setShader(new LinearGradient(10, 200, 210, 410, Color.RED, Color.BLUE, Shader.TileMode.REPEAT));
        paint1.setShader(new LinearGradient(0, 0, 210, 410, new int[]{Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN}, new float[]{0.2f, 0.8f, 1.0f, 2.0f}, Shader.TileMode.REPEAT));

        paint2.setShader(new LinearGradient(0, 0, 210, 323, new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE}, null, Shader.TileMode.MIRROR));
        /////////////////////////////////////////////////

      /////////////////////////////////////////////////获取位图;
        srcBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.beauty_one);

        //实例化矩阵
        Matrix matrix=new Matrix();
        matrix.setScale(1F,-1F);
        //生成倒影图
        targetBitmap=Bitmap.createBitmap(srcBitmap,0,0,srcBitmap.getWidth(),srcBitmap.getHeight(),matrix,true);
        mxfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_IN);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth() / 3, getHeight() / 3, paint);

        canvas.drawRect(getWidth() / 3 + 10, 0, getWidth() / 3 * 2, getHeight() / 3, paint1);

        canvas.drawRect(getWidth() / 3 * 2 + 10, 0, getWidth(), getHeight() / 3, paint2);
        /////////////////////////////////////////////////////////////////////////
        //public SweepGradient(float cx, float cy, int color0, int color1)
        /**
         *   cx	渲染中心点x 坐标
         cy	渲染中心点y 坐标
         color0	起始渲染颜色
         color1	结束渲染颜色
         */
        //
        paint4.setShader(new SweepGradient(getWidth()/6,getHeight()/2,Color.RED,Color.YELLOW));
        paint5.setShader(new SweepGradient(getWidth()/3+getHeight()/6-50,getHeight()/2,new int[]{Color.RED,Color.GREEN,Color.BLUE},null));



        canvas.drawRect(0, getHeight()/3, getWidth() / 3, getHeight() / 3*2, paint4);

        canvas.drawRect(getWidth()/3+10, getHeight()/3, getWidth() / 3*2, getHeight() / 3*2, paint5);

        /////////////////////////////////////////////////////////////////////////

/*

        x=getWidth()/2-srcBitmap.getWidth()/2;
        y=getHeight()/2-srcBitmap.getHeight()/2;

        paint3.setShader(new LinearGradient(x,y+srcBitmap.getHeight(),
                x,y+srcBitmap.getHeight()+srcBitmap.getHeight()/4,0xAA000000,
                Color.TRANSPARENT, Shader.TileMode.CLAMP));
        canvas.drawColor(Color.BLACK);

        canvas.drawBitmap(srcBitmap,x,y,null);

        int sc = canvas.saveLayer(x, y + srcBitmap.getHeight(), x + targetBitmap.getWidth(), y + srcBitmap.getHeight() * 2, null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(targetBitmap, x, y + srcBitmap.getHeight(), null);

        paint3.setXfermode(mxfermode);

        canvas.drawRect(x, y + srcBitmap.getHeight(), x + targetBitmap.getWidth(), y + srcBitmap.getHeight() * 2, paint3);

        paint3.setXfermode(null);

        canvas.restoreToCount(sc);
*/



    }
}
