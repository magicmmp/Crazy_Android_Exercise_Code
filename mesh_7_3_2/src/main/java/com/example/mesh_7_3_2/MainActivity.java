package com.example.mesh_7_3_2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
{
    String TAG="HaHa";
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this,R.drawable.p33));
    }

    public class MyView extends View
    {
        private Matrix matrix= new Matrix();
        // 定义两个常量，这两个常量指定该图片横向、纵向上都被划分为20格
        private final int WIDTH = 20;
        private final int HEIGHT = 20;
        // 记录该图片上包含441个顶点
        private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
        // 定义一个数组，保存Bitmap上的21 * 21个点的坐标
        private final float[] verts = new float[COUNT * 2];
        // 定义一个数组，记录Bitmap上的21 * 21个点经过扭曲后的坐标
        // 对图片进行扭曲的关键就是修改该数组里元素的值
        private final float[] orig = new float[COUNT * 2];
        public MyView(Context context, int drawableId)
        {
            super(context);
            setFocusable(true);
            // 根据指定资源加载图片
            bitmap = BitmapFactory.decodeResource(getResources()
                    , drawableId);
            // 获取图片宽度、高度
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            Log.d(TAG, "原图片 宽："+bitmapWidth+",高："+bitmapHeight);
            // 获取窗口管理器
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            // 获得屏幕的宽度
            float screenWidth = metrics.widthPixels;
            float screenHeight=metrics.heightPixels;
            Log.d(TAG, "屏幕 宽："+screenWidth+",高："+screenHeight);
            // 获取图片的缩放比例
            float scaleW = screenWidth / bitmapWidth;
            float scaleH = screenHeight/ bitmapHeight;
            float scale=scaleH<scaleW ? scaleH:scaleW;
            matrix.setScale(scale , scale);
            // 根据原始位图和缩放Matrix创建新图片
            bitmap = Bitmap
                    .createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight , matrix , false);
            // 获取图片宽度、高度
            bitmapWidth = bitmap.getWidth();
            bitmapHeight = bitmap.getHeight();
            Log.d(TAG, "缩放后图片 宽："+bitmapWidth+",高："+bitmapHeight);


            int index = 0;
            for (int y = 0; y <= HEIGHT; y++)
            {
                float fy = bitmapHeight * y / HEIGHT;
                for (int x = 0; x <= WIDTH; x++)
                {
                    float fx = bitmapWidth * x / WIDTH;
                    // 初始化orig、verts数组。 初始化后，orig、verts
                    // 两个数组均匀地保存了21 * 21个点的x,y坐标
                    orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                    orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                    index += 1;
                }
            }
            // 设置背景色
            setBackgroundColor(Color.WHITE);
        }
        @Override
        protected void onDraw(Canvas canvas)
        {
            //对bitmap按verts数组进行扭曲
            //从第一个点（由第5个参数0控制）开始扭曲
            canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts
                    , 0, null, 0,null);
        }
        // 工具方法，用于根据触摸事件的位置计算verts数组里各元素的值
        private void warp(float cx, float cy)
        {
            for (int i = 0; i < COUNT * 2; i += 2)
            {
                float dx = cx - orig[i + 0];
                float dy = cy - orig[i + 1];
                float dd = dx * dx + dy * dy;
                // 计算每个坐标点与当前点（cx、cy）之间的距离
                float d = (float) Math.sqrt(dd);
                // 计算扭曲度，距离当前点（cx、cy）越远，扭曲度越小
                float pull = 100000 / ((float) (dd * d));
                // 对verts数组（保存bitmap上21 * 21个点经过扭曲后的坐标）重新赋值
                if (pull >= 1)
                {
                    verts[i + 0] = cx;
                    verts[i + 1] = cy;
                }
                else
                {
                    // 控制各顶点向触摸事件发生点偏移
                    verts[i + 0] = orig[i + 0] + dx * pull;
                    verts[i + 1] = orig[i + 1] + dy * pull;
                }
            }
            // 通知View组件重绘
            invalidate();
        }
        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            // 调用warp方法根据触摸屏事件的坐标点来扭曲verts数组
            warp(event.getX(), event.getY());
            return true;
        }
    }
}
