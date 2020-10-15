package com.example.wss_2000.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment
 * @ClassName: MainFragment
 * @Description: 主界面Fragment
 * @Author: wangj
 * @CreateDate: 2020/10/15 11:46
 * @Version: 1.0
 */
public class MainFragment extends BaseFragment {

    private static MainFragment fragment;

    public static BaseFragment newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
             fragment = new MainFragment();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main;
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
