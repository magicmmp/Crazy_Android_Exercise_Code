package com.example.callbackhandler_3_3_1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

/**
 * Created by pss on 2019/7/28.
 */

public class MyButton extends Button {
    public MyButton(Context context, AttributeSet set)
    {
        super(context, set);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        super.onKeyDown(keyCode, event);
        Log.d("HaHa", "组件自定义的回调方法：按下了："+keyCode+",KeyEvent: "+event);
        // 返回true，表明该事件不会向外扩散
        return true;
    }
}
