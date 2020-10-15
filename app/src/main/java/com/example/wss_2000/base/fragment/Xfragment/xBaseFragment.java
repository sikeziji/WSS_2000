package com.example.wss_2000.base.fragment.Xfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import me.yokeyword.fragmentation.SupportFragment;

public abstract class xBaseFragment extends SupportFragment {


    /**
     * @return fragment 布局id
     */
    protected abstract int getLayoutResId();

    /**
     * 释放资源
     */
    protected abstract void release();


    /**
     * 初始化Fragment的布局,当要创建视图时调用
     *
     * @return view 返回视图
     */
    protected abstract View initView(View view);


    /**
     * 初始化数据,当ViewCreate被创建是调用此方法
     */
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        initView(view);
        return view;
    }
}
