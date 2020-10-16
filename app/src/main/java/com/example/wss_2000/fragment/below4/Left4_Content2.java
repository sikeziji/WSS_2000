package com.example.wss_2000.fragment.below4;

import android.os.Bundle;
import android.view.View;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below4
 * @ClassName: Left4_Content1
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/16 9:25
 * @Version: 1.0
 */
public class Left4_Content2 extends BaseFragment {

    private static Left4_Content2 fragment;

    public static Left4_Content2 newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left4_Content2();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left4fragment2;
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
