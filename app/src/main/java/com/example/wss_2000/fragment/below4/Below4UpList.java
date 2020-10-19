package com.example.wss_2000.fragment.below4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.base.fragment.FragmentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below4
 * @ClassName: Below4UpList
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/16 9:21
 * @Version: 1.0
 */
public class Below4UpList extends BaseFragment {

    private static Below4UpList fragment;
    @BindView(R.id.btnLeft1)
    ImageButton btnLeft1;
    @BindView(R.id.btnLeft2)
    ImageButton btnLeft2;
    @BindView(R.id.btnLeft3)
    ImageButton btnLeft3;
    @BindView(R.id.layout_left)
    LinearLayout layoutLeft;

    private BaseFragment[] mFragments = new BaseFragment[4];
    private int position = 0, prePosition = 0;


    public static Below4UpList newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Below4UpList();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_below4_up;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {
        addAndshowFragment(position, Left4_Content1.newInstance());
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
                addAndshowFragment(position, Left4_Content1.newInstance());
                break;
            case R.id.btnLeft2:
                position = 2;
                addAndshowFragment(position, Left4_Content2.newInstance());
                break;
            case R.id.btnLeft3:
                position = 3;
                addAndshowFragment(position, Left4_Content3.newInstance());
                break;
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
            FragmentUtils.addFragment(getChildFragmentManager(), mFragments[position], R.id.fragment_below4_up_right);
        }
        FragmentUtils.hideShowFragment(mFragments[prePosition], mFragments[position]);
        prePosition = position;
    }
}
