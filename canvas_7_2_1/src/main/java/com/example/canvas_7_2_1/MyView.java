package com.example.canvas_7_2_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by pss on 2019/8/6.
 */

public class MyView extends View
{
    Path path;
    Paint paint;
    MyView(Context context)
    {
        super(context);
        path = new Path();
        // 初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(100,50);
        path.lineTo(200,50);
        path.lineTo(150,100);
        path.close();
        canvas.drawPath(path, paint);
    }
}
