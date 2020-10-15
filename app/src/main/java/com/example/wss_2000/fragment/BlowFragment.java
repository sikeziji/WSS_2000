package com.example.wss_2000.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.wss_2000.MainActivity;
import com.example.wss_2000.R;
import com.example.wss_2000.base.fragment.BaseFragment;
import com.example.wss_2000.base.fragment.FragmentUtils;
import com.example.wss_2000.fragment.below1.Below1UpList;
import com.example.wss_2000.fragment.below2.Below2UpList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/10/24
 */
public class BlowFragment extends BaseFragment {

    @BindView(R.id.btnBelow1)
    ImageButton btnBelow1;
    @BindView(R.id.btnBelow2)
    ImageButton btnBelow2;
    @BindView(R.id.btnBelow3)
    ImageButton btnBelow3;
    @BindView(R.id.btnBelow4)
    ImageButton btnBelow4;
    @BindView(R.id.btnBelow5)
    ImageButton btnBelow5;

    Unbinder unbinder;
    private BaseFragment[] mFragments = new BaseFragment[4];
    int position = 0, prePosition = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.main_fragment_blow;
    }

    @Override
    protected void release() {

    }

    @Override
    public View initView(View view) {
//
//        mFragments[0] = Below1UpList.newInstance();
//        mFragments[1] = Below2UpList.newInstance();
//        mFragments[2] = Below4UpList.newInstance();
//        mFragments[3] = Below5UpList.newInstance();
//
//        for (BaseFragment mFragment : mFragments) {
//            addFragment(getFragmentManager(), mFragment, R.id.layout_frame_up);
//            hideFragment(mFragment);
//        }
//
//        showFragment(mFragments[0]);
        return null;
    }

    @Override
    protected void initData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnBelow1, R.id.btnBelow2, R.id.btnBelow3, R.id.btnBelow4, R.id.btnBelow5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBelow1:
                FragmentUtils.replaceFragment(getFragmentManager(), R.id.layout_frame_up, new Below1UpList(), false);
                break;
            case R.id.btnBelow2:
                FragmentUtils.replaceFragment(getFragmentManager(), R.id.layout_frame_up, new Below2UpList(), false);
                break;
            case R.id.btnBelow3:
                MainActivity.winID = R.id.btnBelow3;
//                FragmentUtils.replaceFragment(getFragmentManager(), R.id.All, MainFragment.newInstance(), false);
                FragmentUtils.replaceFragment(getFragmentManager(), R.id.layout_frame_up, MainFragment.newInstance(), false);
                break;
            case R.id.btnBelow4:
//                if (doFlowing.get(MainActivity.mCompName).equals(getString(R.string.selfCheck))) {
//                    break;
//                }
//                if (doFlowing.get(MainActivity.mCompName).equals(getString(R.string.waiting_for_instructions))) {
//                FragmentUtils.replaceFragment(getFragmentManager(), R.id.layout_frame_up, new Below4UpList(), false);
//                } else {
//                    setEventBusMessage("MainActivity", 4, 1, 0, 0);
//                    DialogPassword st = new DialogPassword();
//                    Bundle bundle = getAlertBundle("MainActivity", mCompName, "List4-3");
//                    st.setArguments(bundle);
//                    st.show(getChildFragmentManager(), "Dialog_password");
//                }
                break;
            case R.id.btnBelow5:
//                FragmentUtils.replaceFragment(getFragmentManager(), R.id.layout_frame_up, new Below5UpList(), false);
                break;
        }
    }
}
