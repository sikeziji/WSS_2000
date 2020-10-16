package com.example.wss_2000.fragment.below5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.base.fragment.FragmentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below5
 * @ClassName: Blow5UpList
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/16 11:14
 * @Version: 1.0
 */
public class Below5UpList extends BaseFragment {

    private static Below5UpList fragment;

    @BindView(R.id.btnLeft1)
    ImageButton btnLeft1;
    @BindView(R.id.btnLeft2)
    Button btnLeft2;
    @BindView(R.id.btnLeft3)
    Button btnLeft3;
    @BindView(R.id.btnLeft4)
    Button btnLeft4;
    @BindView(R.id.btnLeft5)
    ImageButton btnLeft5;
    @BindView(R.id.btnLeft6)
    ImageButton btnLeft6;

    private BaseFragment[] mFragments = new BaseFragment[6];
    private int position = 0, prePosition = 0;

    public static Below5UpList newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Below5UpList();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_below5_up;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {

        addAndshowFragment(position, Left5_Content1.newInstance());
        return null;
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btnLeft1, R.id.btnLeft2, R.id.btnLeft3, R.id.btnLeft4, R.id.btnLeft5, R.id.btnLeft6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLeft1:
                position = 0;
                addAndshowFragment(position, Left5_Content1.newInstance());
                break;
            case R.id.btnLeft2:
                position = 1;
                addAndshowFragment(position, Left5_Content2.newInstance());
                break;
            case R.id.btnLeft3:
                position = 2;
                addAndshowFragment(position, Left5_Content3.newInstance());
                break;
            case R.id.btnLeft4:
                position = 3;
                addAndshowFragment(position, Left5_Content4.newInstance());
                break;
            case R.id.btnLeft5:
                position = 4;
                addAndshowFragment(position, Left5_Content5.newInstance());
                break;
            case R.id.btnLeft6:
                position = 5;
                addAndshowFragment(position, Left5_Content6.newInstance());
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
            FragmentUtils.addFragment(getChildFragmentManager(), mFragments[position], R.id.fragment_below5_up_right);
        }
        FragmentUtils.hideShowFragment(mFragments[prePosition], mFragments[position]);
        prePosition = position;
    }
}
