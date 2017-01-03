package com.example.designer.studyipc.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.example.designer.studyipc.R;

/**
 * Created by huangcl on 2016/10/13.
 * <p/>
 * drawBitmapMesh(Bitmap bitmap, int meshWidth, int meshHeight,
 * float[] verts, int vertOffset, int[] colors, int colorOffset, Paint paint)
 */
public class CanvasViewOne extends View {
    private static final int WIDTH = 20;//横向分割成网格的数量
    private static final int HEIGHT = 20;//纵向分隔成网格的数量
    private static final int COUNT = (WIDTH + 1) * (HEIGHT + 1);// 横纵向网格交织产生的点数量

    private Bitmap bitmap;
    private float[] verts;//交点的坐标数组


    public CanvasViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CanvasViewOne(Context context) {
        super(context);
        init();
    }

    public CanvasViewOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
      bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.beauty_one);
        //实例化数组
        verts=new float[COUNT*2];
        //生成交点坐标点
        int index=0;
        float multiple=bitmap.getWidth();
        for (int y=0;y<=HEIGHT;y++){
            float fy=bitmap.getHeight()*y/HEIGHT;
            for(int x=0;x<=WIDTH;x++){
                float fx = bitmap.getWidth() * x / WIDTH + ((HEIGHT - y) * 1.0F / HEIGHT * multiple);
                verts[index * 2 + 0] = fx;
                verts[index * 2 + 1] = fy;
                index += 1;
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制网格位图
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
    }
}
