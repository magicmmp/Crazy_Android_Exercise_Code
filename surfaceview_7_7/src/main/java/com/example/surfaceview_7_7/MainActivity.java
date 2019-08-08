package com.example.surfaceview_7_7;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private SurfaceHolder holder;
    private Paint paint;
    final int HEIGHT = 320;
    final int WIDTH = 768;
    final int X_OFFSET = 100;
    private int cx = X_OFFSET;
    // 坐标原点在屏幕上的Y位置
    int centerY = HEIGHT / 2;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SurfaceView surface = (SurfaceView) findViewById(R.id.show);
        // 初始化SurfaceHolder对象
        holder = surface.getHolder();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        Button sin = (Button)findViewById(R.id.sin);
        Button cos = (Button)findViewById(R.id.cos);
        OnClickListener listener = (new OnClickListener()
        {
            @Override
            public void onClick(final View source)
            {
                //画坐标轴作为背景
                drawBack(holder);
                //新曲线的X坐标值初始值
                cx = X_OFFSET;

                new Thread()
                {
                    @Override
                    public void run()
                    {
                        while (cx <= WIDTH)
                        {
                            //计算当前点的Y坐标值
                            int cy = source.getId() == R.id.sin ? centerY
                                    - (int)(100 * Math.sin((cx - 5) * 2
                                    * Math.PI / 150))
                                    : centerY - (int)(100 * Math.cos ((cx - 5)
                                    * 2 * Math.PI / 150));
                            Canvas canvas = holder.lockCanvas(new Rect(cx ,
                                    cy - 2  , cx + 2, cy + 2));
                            //画一个点
                            canvas.drawPoint(cx , cy , paint);
                            cx++;
                            holder.unlockCanvasAndPost(canvas);
                        }
                    }
                }.start();

                /**
                if(task != null)
                {
                    task.cancel();
                }
                task = new TimerTask()
                {
                    public void run()
                    {
                        //计算当前点的Y坐标值
                        int cy = source.getId() == R.id.sin ? centerY
                                - (int)(100 * Math.sin((cx - 5) * 2
                                * Math.PI / 150))
                                : centerY - (int)(100 * Math.cos ((cx - 5)
                                * 2 * Math.PI / 150));
                        Canvas canvas = holder.lockCanvas(new Rect(cx ,
                                cy - 2  , cx + 2, cy + 2));
                        //画一个点
                        canvas.drawPoint(cx , cy , paint);
                        cx =cx+2;
                        if (cx > WIDTH)
                        {
                            task.cancel();
                            task = null;
                        }
                        holder.unlockCanvasAndPost(canvas);
                    }
                };
                timer.schedule(task , 0 , 1);
                 */
            }
        });
        sin.setOnClickListener(listener);
        cos.setOnClickListener(listener);
        holder.addCallback(new Callback()
        {
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height)
            {
                drawBack(holder);
            }
            @Override
            public void surfaceCreated(final SurfaceHolder myHolder){ }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder)
            {
                //timer.cancel();
            }
        });
    }
    private void drawBack(SurfaceHolder holder)
    {
        Canvas canvas = holder.lockCanvas();
        // 绘制白色背景
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStrokeWidth(10);
        // 绘制坐标轴
        canvas.drawLine(X_OFFSET , centerY , WIDTH , centerY , p);
        canvas.drawLine(X_OFFSET , 40 , X_OFFSET , HEIGHT , p);
        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0 , 0 , 0 , 0));
        holder.unlockCanvasAndPost(canvas);
    }
}
