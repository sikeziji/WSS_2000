package com.example.wss_2000;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wss_2000.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void release() {

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;

    }
}