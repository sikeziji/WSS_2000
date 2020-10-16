package com.example.wss_2000.fragment.below2.content1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.wss_2000.MyApplication;
import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.content.MessageContent;
import com.example.wss_2000.util.EventBusUtil;
import com.example.wss_2000.widget.Widget;

import butterknife.BindView;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.below2
 * @ClassName: Left2_Content2
 * @Description: 留样界面
 * @Author: wangj
 * @CreateDate: 2020/10/15 16:27
 * @Version: 1.0
 */
public class Left2_Content1_Reserved extends BaseFragment implements AdapterView.OnItemSelectedListener {

    private static Left2_Content1_Reserved fragment;
    @BindView(R.id.reservedSampleMode)
    Spinner reservedSampleMode;
    private String[] mReservedSampleMode;


    public static Left2_Content1_Reserved newInstance() {
        Bundle args = new Bundle();
        if (fragment == null) {
            fragment = new Left2_Content1_Reserved();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.left2fragment_reserved;
    }

    @Override
    protected void release() {

    }

    @Override
    protected View initView(View view) {

        mReservedSampleMode = getResources().getStringArray(R.array.reservedSampleMode);

        //初始化当前Spinner
        Widget.InitSpinner(MyApplication.getMyAppContext(), reservedSampleMode, mReservedSampleMode, R.layout.simple_spinner_item_out_black, R.layout.simple_spinner_item_in_black);
        reservedSampleMode.setOnItemSelectedListener(this);
        //默认为超标留样
        reservedSampleMode.setSelection(0);

        return null;
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.samplingMode:
                switch (mReservedSampleMode[position]) {
                    case "超标留样":
                        break;
                    case "同步留样":
                        break;
                    case "串口控制留样":
                        break;
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
