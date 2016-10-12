package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangcl on 2016/10/12.
 *
 */
public class FontViewOne extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final String TAG = "fdsg电332*……^&$3$#%^%&*^*)&$%%";
    private static final String LONGTXT="这个方法让我们设置一个最大宽度在不超过这个宽度的范围内返回实际测量值否则停止测量，参数很多但是都很好理解，text表示我们的字符串，start表示从第几个字符串开始测量，end表示从测量到第几个字符串为止，measureForwards表示向前还是向后测量，maxWidth表示一个给定的最大宽度在这个宽度内能测量出几个字符，measuredWidth为一个可选项，可以为空，不为空时返回真实的测量值。同样的方法还有breakText (String text, boolean measureForwards, float maxWidth, float[] measuredWidth)和breakText (char[] text, int index, int count, float maxWidth, float[] measuredWidth)。这些方法在一些结合文本处理的应用里比较常用，比如文本阅读器的翻页效果，我们需要在翻页的时候动态折断或生成一行字符串，这就派上用场了~~~";
    Paint.FontMetrics fonMetrics;
    TextPaint txtPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);//专门绘制Text文本的画笔
    StaticLayout staticLayout;
    public FontViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FontViewOne(Context context) {
        super(context);
        init();
    }

    public FontViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setTextSize(80);
        paint.setColor(Color.BLACK);
        fonMetrics=paint.getFontMetrics();

        txtPaint.setColor(Color.RED);
        txtPaint.setTextSize(20);
        txtPaint.setStrokeWidth(10);

        Log.e("chun", "ascent：" + fonMetrics.ascent);
        Log.e("chun", "top：" + fonMetrics.top);
        Log.e("chun", "leading：" + fonMetrics.leading);
        Log.e("chun", "descent：" + fonMetrics.descent);
        Log.e("chun", "bottom：" + fonMetrics.bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(TAG,0,Math.abs(fonMetrics.top),paint);
        paint.setColor(Color.YELLOW);
        canvas.drawLine(0,fonMetrics.top,getWidth(),fonMetrics.top,paint);
        paint.setColor(Color.RED);
        canvas.drawLine(0,fonMetrics.ascent,getWidth(),fonMetrics.ascent,paint);
        paint.setColor(Color.GREEN);
        canvas.drawLine(0,fonMetrics.bottom,getWidth(),fonMetrics.bottom,paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(0,fonMetrics.descent,getWidth(),fonMetrics.descent,paint);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0,fonMetrics.leading,getWidth(),fonMetrics.leading,paint);

        staticLayout=new StaticLayout(LONGTXT,txtPaint,canvas.getWidth(), Layout.Alignment.ALIGN_CENTER,1.0f,0.0f,false);
        staticLayout.draw(canvas);
        canvas.restore();

    }
}
