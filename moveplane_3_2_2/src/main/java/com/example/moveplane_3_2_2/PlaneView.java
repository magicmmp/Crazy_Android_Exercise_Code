package com.example.moveplane_3_2_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by pss on 2019/7/28.
 */

public class PlaneView extends View
{
    public float currentX;
    public float currentY;
    Bitmap plane;
    public PlaneView(Context context)
    {
        super(context);
        // 定义飞机图片
        plane = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.xinxin);
        setFocusable(true);
    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        // 创建画笔
        Paint p = new Paint();
        // 绘制飞机
        canvas.drawBitmap(plane, currentX, currentY, p);
    }

}
