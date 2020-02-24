package com.bw.huyanyan.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        getData();
    }
    //布局控件
    protected abstract int getLayoutId();
    //找控件
    protected abstract void initView();
    //获取数据
    protected abstract void getData();
}
