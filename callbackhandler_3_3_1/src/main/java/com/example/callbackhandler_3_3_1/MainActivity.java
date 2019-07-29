package com.example.callbackhandler_3_3_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bn = (Button) findViewById(R.id.bn);
        // 为bn绑定事件监听器
        bn.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View source
                    , int keyCode, KeyEvent event) {
                // 只处理按下键的事件
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d("HaHa", "事件监听器的回调方法：按下了："+keyCode+",KeyEvent: "+event);
                }
                // 返回false，表明该事件会向外传播
                return false; // ①
            }
        });
    }
    // 重写onKeyDown方法，该方法可监听它所包含的所有组件的按键被按下事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        super.onKeyDown(keyCode , event);
        Log.d("HaHa", "活动的回调方法：按下了："+keyCode+",KeyEvent: "+event);
        //返回false，表明并未完全处理该事件，该事件依然向外扩散
        return false;
    }
}
