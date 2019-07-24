package com.example.seekbar_and_ratingbar_2_6_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.widget.SeekBar.OnSeekBarChangeListener;
import static android.widget.RatingBar.OnRatingBarChangeListener;

public class MainActivity extends AppCompatActivity
{
    //这几个变量要作为类成员变量定义，不能放在onCreate方法里定义
    //否则在Listener使用会出错
    TextView textView;
    TextView textView2;
    TextView textView3 ;
    String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        textView=(TextView)findViewById(R.id.tv1) ;
        textView2=(TextView)findViewById(R.id.tv2) ;
        textView3=(TextView)findViewById(R.id.text3) ;

        RatingBar ratingBar=(RatingBar)findViewById(R.id.rating);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            // 当拖动条的滑块位置发生改变时触发该方法
            @Override
            public void onProgressChanged(SeekBar arg0, int progress,
                                          boolean fromUser)
            {
                Log.d(TAG, "拖动条的数值："+progress);
              textView.setText(""+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar bar)
            {
            }
            @Override
            public void onStopTrackingTouch(SeekBar bar)
            {
            }
        });

        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener()
        {
            // 当星级评分条的评分发生改变时触发该方法
            @Override
            public void onRatingChanged(RatingBar arg0, float rating,
                                        boolean fromUser)
            {
                // 动态改变图片的透明度，其中255是星级评分条的最大值
                // 5个星星就代表最大值255
                Log.d(TAG, "星级评分条的数值："+rating);
                textView2.setText(""+(int) (rating * 255 / 5));
                textView3.setText(""+rating);
            }
        });
    }
}
