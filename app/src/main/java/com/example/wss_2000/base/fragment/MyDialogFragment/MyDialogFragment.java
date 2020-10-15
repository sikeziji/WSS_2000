package com.example.wss_2000.base.fragment.MyDialogFragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


import com.example.wss_2000.base.BaseEventMessage;
import com.example.wss_2000.util.EventBusUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/12/12
 */
public abstract class MyDialogFragment extends DialogFragment {
    public AlertDialog dialog;
    Unbinder unbinder;

    public MyDialogFragment() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (isRegisteredEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    protected abstract int getDialogView();

    protected abstract void InitUI(View var1);

    protected abstract boolean setCanceledOnTouchOutside();

    protected abstract String getTitleName();

    protected abstract String getPositiveButtonName();

    protected abstract String getNegativeButtonName();

    protected abstract void PositiveButtonListener();

    protected abstract void NegativeButtonListener();

    /**
     * 弹窗创建
     *
     * @param savedInstanceState
     * @return
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = Objects.requireNonNull(this.getActivity()).getLayoutInflater();
        View view = inflater.inflate(this.getDialogView(), null);
        if (view != null) {
            unbinder = ButterKnife.bind(this, view);
        }
        if (isRegisteredEventBus()) {
            EventBusUtil.register(this);
        }
        this.InitUI(view);
        this.dialog = (new AlertDialog.Builder(this.getActivity())).setTitle(this.getTitleName()).setView(view)
                .setPositiveButton(this.getPositiveButtonName(), new MyDialogFragment.OkClick())
                .setNegativeButton(this.getNegativeButtonName(), new MyDialogFragment.CancelClick()).create();
        (this.dialog.getWindow()).setBackgroundDrawable(new ColorDrawable());
        this.dialog.setCanceledOnTouchOutside(this.setCanceledOnTouchOutside());

        return this.dialog;
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    protected String getPrompt(String key) {
        Bundle b = this.getArguments();
        assert b != null;
        return b.getString(key);
    }


    private class CancelClick implements DialogInterface.OnClickListener {
        private CancelClick() {
        }

        public void onClick(DialogInterface dialog, int which) {
            MyDialogFragment.this.NegativeButtonListener();
        }
    }

    private class OkClick implements DialogInterface.OnClickListener {
        private OkClick() {
        }

        public void onClick(DialogInterface dialog, int which) {
            MyDialogFragment.this.PositiveButtonListener();
        }
    }


    public void dismiss() {
        dialog.dismiss();
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