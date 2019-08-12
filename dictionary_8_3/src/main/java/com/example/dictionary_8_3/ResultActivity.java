package com.example.dictionary_8_3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by pss on 2019/8/8.
 */

public class ResultActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        ListView listView = (ListView) findViewById(R.id.show);
        Intent intent = getIntent();
        // 获取该intent所携带的数据
        Bundle data = intent.getExtras();
        // 从Bundle数据包中取出数据
        @SuppressWarnings("unchecked")
        List<Map<String, String>> list = (List<Map<String, String>>)
                data.getSerializable("data");
        // 将List封装成SimpleAdapter
        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this
                , list,
                R.layout.line, new String[] { "word", "detail" }
                , new int[] {R.id.word, R.id.detail });
        // 填充ListView
        listView.setAdapter(adapter);
    }
}
