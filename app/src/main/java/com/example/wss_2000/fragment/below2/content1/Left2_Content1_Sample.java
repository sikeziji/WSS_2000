package com.example.wss_2000.fragment.below2.content1;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.wss_2000.MyApplication;
import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.base.fragment.FragmentUtils;
import com.example.wss_2000.content.MessageContent;
import com.example.wss_2000.fragment.below2.content1.sampledetail.Left2_Content1_detail1;
import com.example.wss_2000.fragment.below2.content1.sampledetail.Left2_Content1_detail2;
import com.example.wss_2000.fragment.below2.content1.sampledetail.Left2_Content1_detail3;
import com.example.wss_2000.fragment.below2.content1.sampledetail.Left2_Content1_detail4;
import com.example.wss_2000.fragment.below2.content1.sampledetail.Left2_Content1_detail5;
import com.example.wss_2000.util.EventBusUtil;
import com.example.wss_2000.widget.Widget;

import butterknife.BindView;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2
 * @ClassName: Left2_Content1
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 14:51
 * @Version: 1.0
 */
public class Left2_Content1_Sample extends BaseFragment implements AdapterView.OnItemSelectedListener {


    private static Left2_Content1_Sample fragment;

    @BindView(R.id.samplingMode)
    Spinner samplingMode;

    @BindView(R.id.fragment_sampling)
    FrameLayout fragmentSampling;

    private String[] mSamplingModeArray;

    private BaseFragment[] mFragments = new BaseFragment[6];
    private int prePosition = 0;

    public static BaseFragment newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left2_Content1_Sample();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left2fragment_sampling;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {
        //默认界面为0
        addAndshowFragment(0, Left2_Content1_detail1.newInstance());

        mSamplingModeArray = getResources().getStringArray(R.array.smplingMode);

        //初始化当前Spinner
        Widget.InitSpinner(MyApplication.getMyAppContext(), samplingMode, mSamplingModeArray, R.layout.simple_spinner_item_out_black, R.layout.simple_spinner_item_in_black);
        samplingMode.setOnItemSelectedListener(this);
        //默认为流量等比例
        samplingMode.setSelection(0);


        return null;
    }

    private void addAndshowFragment(int position, BaseFragment fragment) {
        if (mFragments[position] == null) {
            mFragments[position] = fragment;
            FragmentUtils.addFragment(getChildFragmentManager(), mFragments[position], R.id.fragment_sampling);
        }
        FragmentUtils.hideShowFragment(mFragments[prePosition], mFragments[position]);
        prePosition = position;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.samplingMode:
                switch (mSamplingModeArray[position]) {
                    case "流量等比例":
                        addAndshowFragment(position, Left2_Content1_detail1.newInstance());
                        break;
                    case "时间等比例":
                        addAndshowFragment(position, Left2_Content1_detail2.newInstance());
                        break;
                    case "时间定量":
                        addAndshowFragment(position, Left2_Content1_detail3.newInstance());
                        break;
                    case "瞬时采样":
                        addAndshowFragment(position, Left2_Content1_detail4.newInstance());
                        break;
                    case "串口控制":
                        addAndshowFragment(position, Left2_Content1_detail5.newInstance());
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + mSamplingModeArray[position]);
                }
                break;
        }
        //跟新界面
        EventBusUtil.setEventBusMessage("MainActivity", MessageContent.MAINACTIVITY_CLOSEANDHIDE, 0, 0, view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
