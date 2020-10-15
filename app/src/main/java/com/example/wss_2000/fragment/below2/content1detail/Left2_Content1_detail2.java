package com.example.wss_2000.fragment.below2.content1detail;

import android.os.Bundle;
import android.view.View;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2.below2content1
 * @ClassName: Left2_Content1_detail1
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 15:43
 * @Version: 1.0
 */
public class Left2_Content1_detail2 extends BaseFragment {

    private static Left2_Content1_detail2 fragment;

    public static Left2_Content1_detail2 newInstance() {

        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left2_Content1_detail2();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left2_content1_detail2;
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
