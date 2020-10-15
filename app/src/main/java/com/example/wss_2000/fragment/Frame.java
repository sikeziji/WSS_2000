package com.example.wss_2000.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/10/31
 * <p>
 * 分上下两部分，作为Fragment 展现容器
 */
public class Frame extends BaseFragment {


    public static Frame newInstance() {

        Bundle args = new Bundle();

        Frame fragment = new Frame();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frame;
    }

    @Override
    protected void release() {

    }

    @Override
    public View initView(View view) {
        return null;
    }

    @Override
    protected void initData() {

    }
}
