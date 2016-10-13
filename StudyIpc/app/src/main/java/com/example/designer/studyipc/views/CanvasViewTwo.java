package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by huangcl on 2016/10/13.
 */
public class CanvasViewTwo extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path;//路径
    private Path mPath;
    private Path path1;

    public CanvasViewTwo(Context context) {
        super(context);
        init();
    }

    public CanvasViewTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(15);

        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.GREEN);
        paint1.setStrokeWidth(10);

        path = new Path();
        //将路径连接到坐标（100,100）
        //path.lineTo(100, 100);
        //移动点到(100,100)
        path.moveTo(100, 100);
        path.lineTo(300, 700);
        path.moveTo(400, 400);
        path.lineTo(400, 0);

        ////
        mPath = new Path();
        mPath.lineTo(100, 100);

        // 连接路径到点
        mPath.lineTo(300, 100);
        mPath.lineTo(400, 200);
        mPath.lineTo(200, 200);
        mPath.close();

        //
        path1=new Path();
        path1.moveTo(320,320);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawPath(path, paint);

        canvas.drawPath(mPath, paint);

        //连接路径到点
        RectF oval=new RectF(320,320,430,430);
        path1.arcTo(oval,0,90);
        canvas.drawRect(oval,paint1);
        canvas.drawPath(path1,paint1);
    }
}
