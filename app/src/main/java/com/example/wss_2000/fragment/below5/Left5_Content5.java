package com.example.wss_2000.fragment.below5;

import android.os.Bundle;
import android.view.View;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below5
 * @ClassName: Left5_Content1
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/16 11:48
 * @Version: 1.0
 */
public class Left5_Content5 extends BaseFragment {

    private static Left5_Content5 fragment;

    public static Left5_Content5 newInstance() {

        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left5_Content5();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left5fragment5;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {
        return null;
    }

    @Override
    protected void initData() {

    }
}
