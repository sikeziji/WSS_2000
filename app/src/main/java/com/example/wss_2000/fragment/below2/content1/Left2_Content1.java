package com.example.wss_2000.fragment.below2.content1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.base.fragment.FragmentUtils;
import com.example.wss_2000.fragment.below2.content1.sampledetail.Left2_Content1_Sample;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2
 * @ClassName: Left2_Content2
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 16:27
 * @Version: 1.0
 */
public class Left2_Content1 extends BaseFragment {

    private static Left2_Content1 fragment;
    @BindView(R.id.btnLeft1)
    Button btnLeft1;
    @BindView(R.id.btnLeft2)
    Button btnLeft2;
    @BindView(R.id.btnLeft3)
    Button btnLeft3;

    @BindView(R.id.fragment_below2_up_blow)
    FrameLayout fragmentBelow2UpBlow;


    private BaseFragment[] mFragments = new BaseFragment[4];


    private int position = 0, prePosition = 0;


    public static Left2_Content1 newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left2_Content1();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left2fragmentall1;
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

    @OnClick({R.id.btnLeft1, R.id.btnLeft2, R.id.btnLeft3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLeft1:
                position = 0;
                addAndshowFragment(position, Left2_Content1_Sample.newInstance());
                break;
            case R.id.btnLeft2:
                position = 1;
                addAndshowFragment(position, Left2_Content1.newInstance());
                break;
            case R.id.btnLeft3:
                position = 2;
                addAndshowFragment(position, Left2_Content3.newInstance());
        }
    }

    /**
     * 显示或添加当前传入界面
     *
     * @param position
     * @param fragment
     */
    private void addAndshowFragment(int position, BaseFragment fragment) {
        if (mFragments[position] == null) {
            mFragments[position] = fragment;
            FragmentUtils.addFragment(getChildFragmentManager(), mFragments[position], R.id.fragment_below2_up_right);
        }
        FragmentUtils.hideShowFragment(mFragments[prePosition], mFragments[position]);
        prePosition = position;
    }

}
