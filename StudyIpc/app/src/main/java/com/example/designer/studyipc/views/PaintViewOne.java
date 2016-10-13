package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by huangcl on 2016/10/11.
 */
public class PaintViewOne extends View {

    /**
     * Paint
     * SET(1, "void set(Paint paint)设置一只画笔，setter。"),
     SETARG(2,"void setARGB(int a,int r,int g,int b),设置Paint对象颜色，a代表透明度，r，g，b代表颜色值。"),
     SETALPHA(3,"void setAlpha(int a),设置alpha透明度，范围为0~255。"),
     SETANTIALIAS(4,"void setAntiAlias(boolean aa),抗锯齿。"),
     SETCOLOR(6,"void setColor(int color),为画笔设置颜色。"),
     SETCOLORFILTE(7,"ColrFilter setColorFilter(ColorFilter filter),设置颜色过滤，ColorFilter有三个子类去实现ColorMatrixColorFilter、LightingColorFilter和PorterDuffColorFilter"),
     SETDITHER(9,"void setDither(boolean dither),设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰。"),
     SETFAKEBOLDTEXT(11,"void setFakeBoldText(boolean fakeBoldText)设置伪粗体文本"),
     setFilterBitmap(12,"void setFilterBitmap(boolean filter)设置位图进行滤波处理"),
     setHinting(15,"void setHinting (int mode),Added in API level 14，设置暗示模式，HINTING_OFF 或 HINTING_ON"),
     setLetterSpacing(16,"void setLetterSpacing (float letterSpacing),Added in API level 21，设置文本字母间距，默认0，负值收紧文本"),
     setLinearText(17,"void setLinearText(boolean linearText),设置线性文本"),
     setMaskFilter(18,"MaskFilter setMaskFilter (MaskFilter maskfilter),设置滤镜的效果，MaskFilter有两个子类实现BlurMaskFilter, EmbossMaskFilter"),
     setPathEffect(19,"PathEffect  setPathEffect(PathEffect effect),设置路径效果，PathEffect有6个子类实现ComposePathEffect, CornerPathEffect, DashPathEffect, DiscretePathEffect, PathDashPathEffect, SumPathEffect"),
     setRasterizer(20,"Rasterizer setRasterizer(Rasterizer rasterizer)设置光栅化，API21已过时。"),
     setShader(21,"Shader  setShader(Shader shader),设置着色器，Shader 子类实现有BitmapShader, ComposeShader, LinearGradient, RadialGradient, SweepGradient"),
     setShadowLayer(22,"void setShadowLayer(float radius, float dx, float dy, int shadowColor),图形添加一个阴影层效果"),
     setStrikeThruText(23,"void setStrikeThruText (boolean strikeThruText),设置删除线"),
     setStrokeCap(24,"void setStrokeCap (Paint.Cap cap),当设置setStyle是Stroke或StrokeAndFill，设置笔刷的图形样式，如圆形样式Cap.ROUND或方形样式Cap.SQUARE"),
     setStrokeJoin(25,"void setStrokeJoin (Paint.Join join),当设置setStyle是Stroke或StrokeAndFill，设置绘制时各图形的结合方式，如影响矩形角的外轮廓"),
     setStrokeMiter(26,"void setStrokeMiter (float miter),当设置setStyle是Stroke或StrokeAndFill，设置斜切"),
     setStrokeWidth(27,"void setStrokeWidth (float width),当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度"),
     setStyle(28,"void setStyle (Paint.Style style),设置画笔样式，画笔样式分三种：\n" +
     "Paint.Style.STROKE：描边\n" +
     "Paint.Style.FILL_AND_STROKE：描边并填充\n" +
     "Paint.Style.FILL：填充"),
     setSubpixelText(29,"void setSubpixelText (boolean subpixelText),有助于文本在LCD屏幕上的显示效果"),
     setTextAlign(30,"void setTextAlign(Paint.Align align),设置文本对齐"),
     setTextScaleX(32,"void setTextScaleX(float scaleX),设置文本缩放倍数，1.0f为原始"),
     setTextSize(33,"void setTextSize(float textSize),设置字体大小"),
     setTextSkewX(34,"void setTextSkewX (float skewX),设置斜体文字，skewX为倾斜弧度，默认值0，大于0，向左斜，小于0，向右斜"),
     setTypeface(35,"Typeface  setTypeface(Typeface typeface),设置字体，Typeface包含了字体的类型，粗细，还有倾斜、颜色等。"),
     setUnderlineText(36,"void setUnderlineText(boolean underlineText),设置下划线"),
     setXfermode(37,"Xfermode setXfermode (Xfermode xfermode),设置图像混合模式，Xfermode 有个子类去实现PorterDuffXfermode");
     */


    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);//定义抗锯齿画笔
    public static final String TAG=PaintViewOne.class.getSimpleName();

    public PaintViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        //画笔初始化都的写在构造函数内，不然可能导致频繁初始化，要是又频繁实例化就更不好了。
        init();
        Log.e(TAG,TAG+"------->PaintViewOne()");
    }


    public PaintViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.e(TAG,TAG+"------->PaintViewOne()");
    }

    public PaintViewOne(Context context) {
        super(context);
        init();
        Log.e(TAG,TAG+"------->PaintViewOne()");
    }
    private void init() {
        paint.setColor(Color.YELLOW);
        paint.setTextSize(20);
        paint.setStrokeMiter(10);//画笔粗细
        paint.setStyle(Paint.Style.STROKE);//"Paint.Style.STROKE：描边 Paint.Style.FILL_AND_STROKE：描边并填充 Paint.Style.FILL：填充";
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG,TAG+"------->onSizeChanged()");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG,TAG+"------->onMeasure()");
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG,TAG+"------->onLayout()");
    }

    /**
     * 重写onDraw()方法，会被频繁调用
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,TAG+"------->onDraw()");
        canvas.drawColor(Color.RED);//设置画布颜色
        paint.setTextSize(40);
        canvas.drawText("画一条点（0，100）到点（200，200）的线",0,100,paint);//第二，三 两个参数是表示距离x,y,轴的距离.
        canvas.drawLine(0,100,200,200,paint);

        canvas.drawText("画一个距左Y轴0，上距X轴300，右距Y轴200,下距X轴500的实心矩形。",0,200,paint);//第二，三 两个参数是表示距离x,y,轴的距离.
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(0,300,200,500,paint);//分别到 X,Y 轴的距离.
        canvas.drawText("画空心矩形。",0,600,paint);//第二，三 两个参数是表示距离x,y,轴的距离.
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(400,400,800,800,paint);//分别到 X,Y 轴的距离.

        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,50,paint);
        Log.e("chun",getHeight()+"\t"+getWidth());
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(500,600,500,paint);//x,y（500，600）确定一个点，半径500

//        canvas.drawRoundRect(getLeft(),getTop(),getRight(),getBottom(),200,300,paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(300,200,800,600,paint);
        paint.setColor(Color.WHITE);
        //前四个参数为矩形，范围，90代表从90度（0度在手表三点中方形）开始画，200度处结束
       // canvas.drawArc(300,200,800,600,90,200,true,paint);
    }
}
