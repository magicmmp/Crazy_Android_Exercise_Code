package com.example.bouncingballs_7_6;

import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by pss on 2019/8/7.
 */

public class ShapeHolder
{

    private float x = 0, y = 0;
    private ShapeDrawable shape;
    private int color;
    private RadialGradient gradient;
    private float alpha = 0.5f;
    private Paint paint;

    public ShapeHolder(ShapeDrawable s)
    {
        shape = s;
    }
    public float getWidth()
    {
        return shape.getShape().getWidth();
    }
    public void setWidth(float width)
    {
        Shape s = shape.getShape();
        s.resize(width, s.getHeight());
    }
    public float getHeight()
    {
        return shape.getShape().getHeight();
    }
    public void setHeight(float height)
    {
        Shape s = shape.getShape();
        s.resize(s.getWidth(), height);
    }
    public float getX()
    {
        return x;
    }
    public void setX(float x)
    {
        this.x = x;
    }
    public float getY()
    {
        return y;
    }
    public void setY(float y)
    {
        this.y = y;
    }
    public ShapeDrawable getShape()
    {
        return shape;
    }
    public void setShape(ShapeDrawable shape)
    {
        this.shape = shape;
    }
    public int getColor()
    {
        return color;
    }
    public void setColor(int color)
    {
        this.color = color;
    }
    public RadialGradient getGradient()
    {
        return gradient;
    }
    public void setGradient(RadialGradient gradient)
    {
        this.gradient = gradient;
    }
    public float getAlpha()
    {
        return alpha;
    }
    public void setAlpha(float alpha)
    {
        this.alpha = alpha;
        //ObjectAnimator不用注册监听器，但需要setter方法设置shape透明度，来让动画自动调用，
        //以应用新的透明度到图形上。
        shape.setAlpha((int)(alpha*255));
    }
    public Paint getPaint()
    {
        return paint;
    }
    public void setPaint(Paint paint)
    {
        this.paint = paint;
    }
}
