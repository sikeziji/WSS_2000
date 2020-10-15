package com.example.wss_2000.base.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wss_2000.MainActivity;
import com.example.wss_2000.MyApplication;
import com.example.wss_2000.base.ActivityFragmentFunction.FunctionManager;
import com.example.wss_2000.base.BaseEventMessage;
import com.example.wss_2000.util.EventBusUtil;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected FunctionManager mFunctionsManager;
    Unbinder unbinder;
    private boolean isFirstLoad = true; // 是否第一次加载

    /**
     * @return fragment 布局id
     */
    protected abstract int getLayoutResId();

    /**
     * 释放资源
     */
    protected abstract void release();


    /**
     * 初始化Fragment的布局,当要创建视图时调用
     *
     * @return view 返回视图
     */
    protected abstract View initView(View view);


    /**
     * 初始化数据,当ViewCreate被创建是调用此方法
     */
    protected abstract void initData();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        if (view != null) {
            unbinder = ButterKnife.bind(this, view);
        }
        initView(view);
        if (isRegisteredEventBus()) {
            EventBusUtil.register(this);
        }
        return view;
    }


    public void setFunctionsManager(FunctionManager functionsManager) {
        if (functionsManager != null) {
            this.mFunctionsManager = functionsManager;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据

    }

    @Override

    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            // 将数据加载逻辑放到onResume()方法中
            initData();
            initEvent();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        resetState();
    }

    @Override
    public void onDestroy() {
        //fixInputMethodManagerLeak(MyApplication.getMyAppContext());

        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(MyApplication.getMyAppContext());
        refWatcher.watch(this);
        release();
        unbinder.unbind();
        if (isRegisteredEventBus()) {
            EventBusUtil.unregister(this);
        }

    }


    /**
     * 清除状态
     */
    private void resetState() {

        isFirstLoad = true;
    }

    /**
     * 初始化事件
     */
    protected void initEvent() {

    }


    //只有API-23以上版本才有该方法
    @TargetApi(23)
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(@NotNull Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    private void onAttachToContext(Context context) {
        if (context instanceof MainActivity) {
            MainActivity mBaseActivity = (MainActivity) context;
            //mBaseActivity.setFunctionsForFragment(getTag());
        }
    }


    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(BaseEventMessage event) {
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveStickyEvent(BaseEventMessage event) {
    }


}
