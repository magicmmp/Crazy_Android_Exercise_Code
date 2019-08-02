package com.example.animationdrawable_6_4_6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView image = (ImageView)findViewById(R.id.image);
        // 加载动画资源
        final Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.my_anim);
        // 设置动画结束后保留结束状态
       // anim.setFillAfter(true);  // ①
        anim.setRepeatMode(Animation.RESTART);
        anim.setRepeatCount(5);
        Button bn = (Button) findViewById(R.id.bn);
        bn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 开始动画
                image.startAnimation(anim);
            }
        });

    }
}
