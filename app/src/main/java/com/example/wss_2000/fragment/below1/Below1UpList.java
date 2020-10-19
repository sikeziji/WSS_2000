package com.example.wss_2000.fragment.below1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.fragment.Below1
 * @ClassName: Below1UpList
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/15 13:50
 * @Version: 1.0
 */
public class Below1UpList extends BaseFragment {


    @BindView(R.id.layout_left)
    LinearLayout layoutLeft;
    @BindView(R.id.btnLeft1)
    ImageButton btnLeft1;
    @BindView(R.id.btnLeft2)
    ImageButton btnLeft2;
    @BindView(R.id.btnLeft3)
    ImageButton btnLeft3;
    @BindView(R.id.btnLeft4)
    ImageButton btnLeft4;
    @BindView(R.id.btnLeft5)
    ImageButton btnLeft5;
    private BaseFragment[] mFragments = new BaseFragment[6];
    private int position = 0, prePosition = 0;

    public static Below1UpList newInstance() {
        Bundle args = new Bundle();
        Below1UpList fragment = new Below1UpList();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_below1_up;
    }

    @Override
    protected void release() {

    }


    @Override
    public View initView(View view) {

        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @OnClick({R.id.btnLeft1, R.id.btnLeft2, R.id.btnLeft3, R.id.btnLeft4, R.id.btnLeft5})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLeft1:
                break;
            case R.id.btnLeft2:

                break;
            case R.id.btnLeft3:

                break;
            case R.id.btnLeft4:
                break;
            case R.id.btnLeft5:
                break;
        }
    }
}
