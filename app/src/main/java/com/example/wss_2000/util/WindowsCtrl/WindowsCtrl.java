package com.example.wss_2000.util.WindowsCtrl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/4
 * 屏幕全屏控制
 */
public class WindowsCtrl {

    /**
     * 设置屏幕全屏，（沉浸模式）
     *
     * @param mActivityWindow
     */
    public static void setFullScreenSticky(Window mActivityWindow) {
        int iView = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        mActivityWindow.getDecorView().setSystemUiVisibility(iView);
    }


    /**
     * 设置屏幕全屏，（黏性沉浸模式）
     *
     * @param window
     */
    public static void setFullScreenNoSticky(Window window) {
        int iView = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.getDecorView().setSystemUiVisibility(iView);
    }

    /**
     * 设置 导航栏
     *
     * @param show true :显示 false 隐藏
     */
    public static void setNavigationBar(boolean show, Context context) {
        Intent intent = new Intent();
        if (!show) {
            //设置广播发送隐藏虚拟按键命令
            intent.setAction("com.android.intent.action.NAVBAR_SHOW");
            intent.putExtra("cmd", "hide");
            context.sendOrderedBroadcast(intent, null);
        } else {
            //设置广播发送显示虚拟按键命令
            intent.setAction("com.android.intent.action.NAVBAR_SHOW");
            intent.putExtra("cmd", "show");
            context.sendOrderedBroadcast(intent, null);
        }
    }


    /**
     * 设置  状态栏
     *
     * @param show true :显示 false 隐藏
     */
    public static void setStatusBar(boolean show, Activity mActivity) {
        if (!show) { //隐藏状态栏
            WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            mActivity.getWindow().setAttributes(lp);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        } else { //显示状态栏
            WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            mActivity.getWindow().setAttributes(lp);
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

}
