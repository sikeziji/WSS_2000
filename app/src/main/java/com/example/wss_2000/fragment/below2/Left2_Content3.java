package com.example.wss_2000.fragment.below2;

import android.os.Bundle;
import android.view.View;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2
 * @ClassName: Left2_Content2
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 16:27
 * @Version: 1.0
 */
public class Left2_Content3 extends BaseFragment {

    private static Left2_Content3 fragment;

    public static Left2_Content3 newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left2_Content3();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left2fragment3;
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
