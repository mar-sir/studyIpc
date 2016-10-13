package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangcl on 2016/10/13.
 */
public class WaveView extends View {
    private Path path;
    private Paint mPaint;

    private int width, height;//控件宽高
    private float ctrX, ctrY;//控制点的xy坐标
    private float waveY;//整个Wave顶部两端点的Y坐标,该坐标与控制点的Y坐标增减幅一致
    private boolean isInc;//判断控制点是该右移还是左移

    public WaveView(Context context) {
        super(context);
        init();
        //实例化

    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xFFA2d6ae);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("chun", "--------->onSizeChange()");
        width = w;
        height = h;
        //计算控制点Y坐标
        waveY = 1 / 8F * height;
        //计算端点Y坐标
        ctrY = -1 / 16f * height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * 设置Path起点
         * 注意我将Path的起点设置在了控件的外部看不到的区域
         * 如果我们将起点设置在控件左端x=0的位置会使得贝塞尔曲线变得生硬
         * 至于为什么刚才我已经说了
         * 所以我们稍微让起点往“外”走点
         */
        path.moveTo(-1 / 4F * width, waveY);
        /**
         * 以二阶曲线的方式通过控制点连接位于控件右边的终点
         * 终点的位置也是在控件外部
         * 我们只需不断让ctrX的大小变化即可实现“浪”的效果
         */

        path.quadTo(ctrX,ctrY,width+1/4f*width,waveY);

        // 围绕控件闭合曲线
        path.lineTo(width + 1 / 4F * width, height);

        path.lineTo(-1 / 4F * width, height);
        path.close();

        canvas.drawPath(path, mPaint);

        /*
         * 当控制点的x坐标大于或等于终点x坐标时更改标识值
         */
        if (ctrX >= width + 1 / 4F * width) {
            isInc = false;
        }
        /*
         * 当控制点的x坐标小于或等于起点x坐标时更改标识值
         */
        else if (ctrX <= -1 / 4F * width) {
            isInc = true;
        }

        // 根据标识值判断当前的控制点x坐标是该加还是减
        ctrX = isInc ? ctrX + 20 : ctrX - 20;

        /*
         * 让“水”不断减少
         */
        if (ctrY <= height) {
            ctrY += 2;
            waveY += 2;
        }
        path.reset();
        postInvalidate();
    }

}
