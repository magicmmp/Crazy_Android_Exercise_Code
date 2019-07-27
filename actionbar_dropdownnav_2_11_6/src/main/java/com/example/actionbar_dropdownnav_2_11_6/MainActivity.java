package com.example.actionbar_dropdownnav_2_11_6;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity implements
        ActionBar.OnNavigationListener{

    private  static  final String TAG="HaHa";
    private static final String SELECTED_ITEM = "selected_item";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar==null)
            Log.d(TAG, "无法获得actionBar。");
        // 设置ActionBar是否显示标题
        actionBar.setDisplayShowTitleEnabled(true);
        // 设置导航模式，使用List导航
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        // 为actionBar安装ArrayAdapter和导航条目监听器
        actionBar.setListNavigationCallbacks(
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, new String[]
                        {"第一页","第二页","第三页" }), this);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        if (savedInstanceState.containsKey(SELECTED_ITEM))
        {
            Log.d(TAG, "运行了onRestoreInstanceState（）。");
            // 选中前面保存的索引对应的Fragment页
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(SELECTED_ITEM));
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        Log.d(TAG, "运行了onSaveInstanceState(Bundle outState)。");
        // 将当前选中的Fragment页的索引保存到Bundle中
        outState.putInt(SELECTED_ITEM,
                getSupportActionBar().getSelectedNavigationIndex());
    }
    // 当导航项被选中时激发该方法
    @Override
    public boolean onNavigationItemSelected(int position, long id)
    {
        // 创建一个新的Fragment对象
        Fragment fragment = new DummyFragment();
        // 创建一个Bundle对象，用于向Fragment传入参数
        Bundle args = new Bundle();
        args.putInt(DummyFragment.ARG_SECTION_NUMBER, position + 1);
        // 向fragment传入参数
        fragment.setArguments(args);
        // 获取FragmentTransaction对象
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // 使用fragment代替该Activity中的container组件
        ft.replace(R.id.container, fragment);
        // 提交事务
        ft.commit();
        Log.d(TAG, "换了一个碎片。");
        return true;
    }
}
