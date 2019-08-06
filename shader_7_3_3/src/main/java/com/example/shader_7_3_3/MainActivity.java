package com.example.shader_7_3_3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements OnClickListener
{
    String TAG="HaHa";
    private Matrix matrix= new Matrix();
    float viewW;
    float viewH;
    // 声明位图渲染对象
    private Shader[] shaders = new Shader[5];
    // 声明颜色数组
    private int[] colors;
    // 自定义视图类
    MyView myView;

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        myView = (MyView)findViewById(R.id.my_view);
        viewW=myView.getWidth();
        viewH=myView.getHeight();
        Log.d(TAG, "控件 宽："+viewW+",高："+viewH);



        // 获得Bitmap实例
        Bitmap bm = BitmapFactory.decodeResource(getResources()
                , R.drawable.p33);
        // 获取图片宽度、高度
        int bitmapWidth = bm.getWidth();
        int bitmapHeight = bm.getHeight();
        Log.d(TAG, "源图片 宽："+bitmapWidth+",高："+bitmapHeight);

        // 获取图片的缩放比例
        float scaleW = viewW / bitmapWidth;
        float scaleH = viewH/ bitmapHeight;
        float scale=scaleH<scaleW ? scaleH:scaleW;
        matrix.setScale(scale , scale);
        // 根据原始位图和缩放Matrix创建新图片
        bm = Bitmap.createBitmap(bm, 0, 0, bitmapWidth, bitmapHeight , matrix , false);
        // 获取图片宽度、高度
        bitmapWidth = bm.getWidth();
        bitmapHeight = bm.getHeight();
        Log.d(TAG, "缩放后图片 宽："+bitmapWidth+",高："+bitmapHeight);

        // 设置渐变的颜色组，也就是按红、绿、蓝的方式渐变
        colors = new int[]  { Color.RED, Color.GREEN, Color.BLUE };
        // 实例化BitmapShader，x坐标方向重复图形，y坐标方向镜像图形
        shaders[0] = new BitmapShader(bm, Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        // 实例化LinearGradient
        shaders[1] = new LinearGradient(0, 0, 0, 100
                , colors, null, Shader.TileMode.REPEAT);
        // 实例化RadialGradient
        shaders[2] = new RadialGradient(500, 500, 100, colors, null,
                Shader.TileMode.REPEAT);
        // 实例化SweepGradient
        shaders[3] = new SweepGradient(500, 500, colors, null);
        // 实例化ComposeShader
        shaders[4] = new ComposeShader(shaders[1], shaders[2],
                PorterDuff.Mode.DARKEN);
        Button bn1 = (Button)findViewById(R.id.bn1);
        Button bn2 = (Button)findViewById(R.id.bn2);
        Button bn3 = (Button)findViewById(R.id.bn3);
        Button bn4 = (Button)findViewById(R.id.bn4);
        Button bn5 = (Button)findViewById(R.id.bn5);
        bn1.setOnClickListener(this);
        bn2.setOnClickListener(this);
        bn3.setOnClickListener(this);
        bn4.setOnClickListener(this);
        bn5.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onClick(View source)
    {
        switch(source.getId())
        {
            case R.id.bn1:
                myView.paint.setShader(shaders[0]);
                break;
            case R.id.bn2:
                myView.paint.setShader(shaders[1]);
                break;
            case R.id.bn3:
                myView.paint.setShader(shaders[2]);
                break;
            case R.id.bn4:
                myView.paint.setShader(shaders[3]);
                break;
            case R.id.bn5:
                myView.paint.setShader(shaders[4]);
                break;
        }
        // 重绘界面
        myView.invalidate();
    }
}
