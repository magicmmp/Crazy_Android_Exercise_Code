package com.example.togglebutton_2_3_6;

import android.os.Bundle;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggle;
    Switch switcher;
    @Override

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggle = (ToggleButton)findViewById(R.id.toggle);
        switcher = (Switch)findViewById(R.id.switcher);
        final LinearLayout test = (LinearLayout)findViewById(R.id.test);
        OnCheckedChangeListener listener = new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton button
                    , boolean isChecked)
            {
                if(isChecked)
                {
                    // 设置LinearLayout垂直布局
                    test.setOrientation(1);
                    toggle.setChecked(true);
                    switcher.setChecked(true);
                }
                else
                {
                    // 设置LinearLayout水平布局
                    test.setOrientation(0);
                    toggle.setChecked(false);
                    switcher.setChecked(false);
                }
            }
        };
        toggle.setOnCheckedChangeListener(listener);
        switcher.setOnCheckedChangeListener(listener);
    }
}
