package com.example.wss_2000.fragment.below2;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.wss_2000.R;
import com.example.wss_2000.base.BaseEventMessage;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.base.fragment.FragmentUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2
 * @ClassName: Below2UpList
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 14:03
 * @Version: 1.0
 */
public class Below2UpList extends BaseFragment {

    @BindView(R.id.btnLeft1)
    Button btnLeft1;
    @BindView(R.id.btnLeft2)
    Button btnLeft2;
    @BindView(R.id.btnLeft3)
    Button btnLeft3;

    private BaseFragment[] mFragments = new BaseFragment[3];


    private int position = 0, prePosition = 0;

    public static Below2UpList newInstance() {
        Bundle args = new Bundle();
        Below2UpList fragment = new Below2UpList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_below2_up;
    }

    @Override
    protected void release() {

    }

    @Override
    public View initView(View view) {
        addAndshowFragment(position, Left2_Content1.newInstance());
//
//        if (getModePermissions(mCompName, "测量参数")) {
//            showFragment(mFragments[0]);
//            prePosition = 0;
//        } else {
//            if (mFragments[1] == null) {
//                mFragments[1] = Left2_Content2.newInstance();
//                addFragment(getChildFragmentManager(), mFragments[1], R.id.fragment_below2_up_right);
//            }
//            hideShowFragment(mFragments[0], mFragments[1]);
//            prePosition = 1;
//        }
        return null;
    }

    @Override
    protected void initData() {
        showLeftWidget();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @OnClick({R.id.btnLeft1, R.id.btnLeft2, R.id.btnLeft3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLeft1:
                position = 0;
                addAndshowFragment(position, Left2_Content1.newInstance());
                break;
            case R.id.btnLeft2:
                position = 1;
                addAndshowFragment(position, Left2_Content2.newInstance());
                break;
            case R.id.btnLeft3:
                position = 2;
                addAndshowFragment(position, Left2_Content3.newInstance());
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
            FragmentUtils.addFragment(getChildFragmentManager(), mFragments[position], R.id.fragment_below2_up_right);
        }
        FragmentUtils.hideShowFragment(mFragments[prePosition], mFragments[position]);
        prePosition = position;
    }

    public void showLeftWidget() {
//
//        if (!getModePermissions(mCompName, "测量参数")) {
//            btnLeft1.setVisibility(View.GONE);
//        } else {
//            btnLeft1.setVisibility(View.VISIBLE);
//        }
//        if (!getModePermissions(mCompName, "量程选择")) {
//            btnLeft3.setVisibility(View.GONE);
//        } else {
//            btnLeft3.setVisibility(View.VISIBLE);
//        }
//        if (!getModePermissions(mCompName, "其他设置")) {
//            btnLeft4.setVisibility(View.GONE);
//        } else {
//            btnLeft4.setVisibility(View.VISIBLE);
//        }
//        if (!getModePermissions(mCompName, "时间管理")) {
//            btnLeft5.setVisibility(View.GONE);
//        } else {
//            btnLeft5.setVisibility(View.VISIBLE);
//        }
//        if (!getModePermissions(mCompName, "余量管理")) {
//            btnLeft6.setVisibility(View.GONE);
//        } else {
//            btnLeft6.setVisibility(View.VISIBLE);
//        }
    }


    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return true;
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(BaseEventMessage event) {
        Message msg = event.getMsg();
        String sCode = event.getsCode();
        if (sCode.equals("Below2UpList")) {
            switch (msg.what) {
                case 1:
                    showLeftWidget();
                    break;
            }
        }
    }
}
