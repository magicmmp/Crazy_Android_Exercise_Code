package com.example.adapterviewflipper_2_5_7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import static android.view.ViewGroup.LayoutParams;

public class MainActivity extends AppCompatActivity {
    int[] imageIds = new int[]
            {
                R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12,
                R.drawable.p13, R.drawable.p14, R.drawable.p15, R.drawable.p16,
                R.drawable.p17, R.drawable.p18, R.drawable.p19, R.drawable.p20,
            };
    private AdapterViewFlipper flipper;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flipper = (AdapterViewFlipper) findViewById(R.id.flipper);
        // 创建一个BaseAdapter对象，该对象负责提供Gallery所显示的列表项
        BaseAdapter adapter = new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                return imageIds.length;
            }
            @Override
            public Object getItem(int position)
            {
                return position;
            }
            @Override
            public long getItemId(int position)
            {
                return position;
            }
            // 该方法返回的View代表了每个列表项
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                // 创建一个ImageView
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(imageIds[position]);
                // 设置ImageView的缩放类型
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // 为imageView设置布局参数
                imageView.setLayoutParams(new ViewGroup.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };
        flipper.setAdapter(adapter);
    }
    public void prev(View source)
    {
        // 显示上一个组件
        flipper.showPrevious();
        // 停止自动播放
        flipper.stopFlipping();
    }
    public void next(View source)
    {
        // 显示下一个组件。
        flipper.showNext();
        // 停止自动播放
        flipper.stopFlipping();
    }
    public void auto(View source)
    {
        // 开始自动播放
        flipper.startFlipping();
    }
}
