package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangcl on 2016/10/13.
 */
public class MultiCricleView extends View {
    private static final float STROKE_WIDTH = 1F / 256F, // 描边宽度占比
            SPACE = 1f / 64F,//间隔
            LINE_LENGTH = 3F / 32F, // 线段长度占比
            CRICLE_LARGER_RADIU = 3F / 32F,// 大圆半径
            CRICLE_SMALL_RADIU = 5F / 64F,// 小圆半径
            ARC_RADIU = 1F / 8F,// 弧半径
            ARC_TEXT_RADIU = 5F / 32F;// 弧围绕文
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint arcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);

    private TextPaint txtPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
    private int size;//控件边长
    private float strokeWidth;//描边宽度
    private float ccx, ccy;//中心圆圆心坐标
    private float largeCricleRadiu;//大圆半径
    private float lineLength;//线长
    private float space;//大圆小圆线段两端间隔
    private float smallCricleRadiu;
    private float textOffsetY;//文本偏移量


    public MultiCricleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiCricleView(Context context) {
        super(context);
        init();
    }

    public MultiCricleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.ROUND);

        /*
         * 初始化文字画笔
         */
        txtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        txtPaint.setColor(Color.WHITE);
        txtPaint.setTextSize(30);
        txtPaint.setTextAlign(Paint.Align.CENTER);

        textOffsetY = (txtPaint.descent() + txtPaint.ascent()) / 2;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //强制长宽一样
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //获取控件边长
        size = w;
        //计算参数
        calculation();

    }

    private void calculation() {
        //计算描边宽度
        strokeWidth = STROKE_WIDTH * size;
        //计算大圆半径
        largeCricleRadiu = size * CRICLE_LARGER_RADIU;
        // 计算线段长度
        lineLength = size * LINE_LENGTH;
        //计算小圆半径
        smallCricleRadiu=size*CRICLE_SMALL_RADIU;

        //计算小圆线段两端间隔
        space = size * SPACE;
        //
        //计算中心圆圆心坐标
        ccx = size / 2;
        ccy = size / 2 + size * CRICLE_LARGER_RADIU;
        // 设置描边宽度
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制背景
        canvas.drawColor(0xFFF29B76);

        // 绘制中心圆
        canvas.drawCircle(ccx, ccy, largeCricleRadiu, paint);
        /**
         * 绘制左上方的图形
         */
        canvas.save();
        ////把当前画布的原点移到(10,10),后面的操作都以(10,10)作为参照点，默认原点为(0,0)
        canvas.translate(ccx, ccy);
        //线长 和半径是一样的
        //画布逆时针旋转30度
        // 依次画：线-圈-线-圈
        canvas.rotate(-30);
        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, paint);
        canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, paint);
        canvas.drawLine(0, -largeCricleRadiu * 4, 0, -lineLength * 5, paint);
        canvas.drawCircle(0, -lineLength * 6, largeCricleRadiu, paint);
        canvas.drawText("Apple", 0, -lineLength * 3 - textOffsetY, txtPaint);
        canvas.drawText("Orange", 0, -lineLength * 6, txtPaint);
        //释放画布,把当前画布返回（调整）到上一个save()状态之前
        canvas.restore();
        /**
         * 绘制右上方图形
         */
        canvas.save();
        canvas.translate(ccx, ccy);
        canvas.rotate(30);
        //线->圈
        canvas.drawLine(0, -largeCricleRadiu, 0, -lineLength * 2, paint);
        canvas.drawCircle(0, -lineLength * 3, largeCricleRadiu, paint);
        canvas.drawText("Tropical", 0, -lineLength * 3 - textOffsetY, txtPaint);
        canvas.restore();
        /**
         * 绘制左下方tuxing
         */
        canvas.save();
        canvas.translate(ccx, ccy);
        canvas.rotate(-100);
        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, -largeCricleRadiu - space, 0, -lineLength * 2 - space, paint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu-space * 2, smallCricleRadiu, paint);
        canvas.drawText("Banana", 0, -lineLength * 2 - smallCricleRadiu-space * 2, txtPaint);
        // 释放画布
        canvas.restore();

        /**
         *绘制下方图形
         */
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccx, ccy);
        canvas.rotate(180);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, -largeCricleRadiu - space, 0, -lineLength * 2 - space, paint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu - space * 2, smallCricleRadiu, paint);
        canvas.drawText("Cucumber", 0, -lineLength * 2 - smallCricleRadiu - space * 2, txtPaint);
        // 释放画布
        canvas.restore();
        /**
         * 绘制右下方图形
         */
        // 锁定画布
        canvas.save();

        // 平移和旋转画布
        canvas.translate(ccx, ccy);
        canvas.rotate(100);

        // 依次画：(间隔)线(间隔)-圈
        canvas.drawLine(0, -largeCricleRadiu - space, 0, -lineLength * 2 - space, paint);
        canvas.drawCircle(0, -lineLength * 2 - smallCricleRadiu - space * 2, smallCricleRadiu, paint);
        canvas.drawText("Vibrators", 0, -lineLength * 2 - smallCricleRadiu - space * 2, txtPaint);
        // 释放画布
        canvas.restore();


    }
}
