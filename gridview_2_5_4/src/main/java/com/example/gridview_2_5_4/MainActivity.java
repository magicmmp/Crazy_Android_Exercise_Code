package com.example.gridview_2_5_4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.view.ViewGroup.LayoutParams;
import android.widget.ViewSwitcher.ViewFactory;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    GridView grid;
    ImageView imageView;
    ImageSwitcher switcher;
    int[] imageIds = new int[]
            {
                      R.drawable.p1 , R.drawable.p2 , R.drawable.p3
                    , R.drawable.p4 , R.drawable.p5 , R.drawable.last
                    , R.drawable.p7 , R.drawable.p8
            };
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建一个List对象，List的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imageIds.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("image", imageIds[i]);
            listItems.add(listItem);
        }
        // 获取显示图片的ImageView
        imageView = (ImageView) findViewById(R.id.imageView);
        // 获取显示图片的ImageSwitcher
        switcher = (ImageSwitcher)
                findViewById(R.id.switcher);
        // 为ImageSwitcher设置图片切换的动画效果
        switcher.setFactory(new ViewFactory()
        {
            @Override
            public View makeView()
            {
                // 创建ImageView对象
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                // 返回ImageView对象
                return imageView;
            }
        });

        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listItems
                // 使用/layout/cell.xml文件作为界面布局
                , R.layout.cell, new String[] { "image" },
                new int[] { R.id.image1 });
        grid = (GridView) findViewById(R.id.grid01);
        // 为GridView设置Adapter
        grid.setAdapter(simpleAdapter);
        // 添加列表项被选中的监听器
        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                // 显示当前被选中的图片
                imageView.setImageResource(imageIds[position]);
                // 显示当前被选中的图片
                switcher.setImageResource(imageIds[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });
        // 添加列表项被单击的监听器
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                // 显示被单击的图片
                imageView.setImageResource(imageIds[position]);
                // 显示当前被选中的图片
                switcher.setImageResource(imageIds[position]);
            }
        });
    }
}
