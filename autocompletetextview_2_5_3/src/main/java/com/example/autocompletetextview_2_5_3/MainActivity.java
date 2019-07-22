package com.example.autocompletetextview_2_5_3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity
{

    AutoCompleteTextView actv;
    MultiAutoCompleteTextView mauto;
    // 定义字符串数组，作为提示的文本
    String[] books = new String[]{
            "bcd疯狂Java讲义",
            "frd疯狂Ajax讲义",
            "uy疯狂XML讲义",
            "dds疯狂Workflow讲义"
    };
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建一个ArrayAdapter，封装数组
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, books);
        actv = (AutoCompleteTextView)findViewById(R.id.auto);
        // 设置Adapter
        actv.setAdapter(aa);
        mauto = (MultiAutoCompleteTextView)findViewById(R.id.mauto);
        // 设置Adapter
        mauto.setAdapter(aa);
        // 为MultiAutoCompleteTextView设置分隔符
        mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
