package com.example.callbackhandler_3_3_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pss on 2019/7/28.
 */

public class DrawView extends View
{
    public float currentX = 40;
    public float currentY = 50;
    // 定义、创建画笔
    Paint p = new Paint();
    public DrawView(Context context, AttributeSet set)
    {
        super(context, set);
    }
    @Override
    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        // 设置画笔的颜色
        p.setColor(Color.RED);
        // 绘制一个小圆（作为小球）
        canvas.drawCircle(currentX, currentY, 15, p);
    }
    @Override
    //基于回调的程序设计更具内聚性，实现跟随手指的小球
    public boolean onTouchEvent(MotionEvent event)
    {
        // 当前组件的currentX、currentY两个属性
        this.currentX = event.getX();
        this.currentY = event.getY();
        // 通知改组件重绘
        this.invalidate();
        // 返回true表明处理方法已经处理该事件
        return true;
    }
}
