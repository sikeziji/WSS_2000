package com.example.wss_2000.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wss_2000.util.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //初始化注册（根据是否注册判断）
        if (isRegisteredEventBus()) {
            EventBusUtil.register(this);
        }
        //初始化视图
        initView();
        //初始化数据
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegisteredEventBus()) {
            EventBusUtil.unregister(this);
        }
        release();
    }


    /**
     * 获取布局
     *
     * @return 布局 ID
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 释放资源
     */
    protected abstract void release();


    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(BaseEventMessage event) {
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveStickyEvent(BaseEventMessage event) {
    }


}
