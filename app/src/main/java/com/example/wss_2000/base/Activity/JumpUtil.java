package com.example.wss_2000.base.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/10/24
 */
public class JumpUtil {


    /**
     * 跳转到目标Activity(传递Parcelable)     *     * @param jumpInterface     * @param aimClass     * @param key     * @param parcelable
     */
    public static void GotoActivity(Context context, JumpInterface jumpInterface, Class aimClass, String key, Parcelable parcelable) {
        if (jumpInterface == null) return;
        Intent intent = new Intent();
        intent.setClass(context, aimClass);
        intent.putExtra(key, parcelable);
        jumpInterface.startActivity(intent);
    }

    /**
     * 跳转到目标Activity(需要带Bundle)     *     * @param bundle     * @param aimClass
     */
    public static void GotoActivity(Context context, JumpInterface jumpInterface, Bundle bundle, Class aimClass) {
        if (jumpInterface == null) return;
        Intent intent = new Intent();
        if (bundle != null) intent.putExtras(bundle);
        intent.setClass(context, aimClass);
        jumpInterface.startActivity(intent);
    }

    /**
     * 跳转到目标Activity     *     * @param aimClass
     */
    public static void GotoActivity(Context context, JumpInterface jumpInterface, Class aimClass) {
        if (jumpInterface == null) return;
        Intent intent = new Intent();
        intent.setClass(context, aimClass);
        jumpInterface.startActivity(intent);
    }

    /**
     * startActivityForResult     *     * @param aimClass     * @param bundle     * @param requestCode
     */
    public static void GotoActivityForResult(Context context, JumpInterface jumpInterface, Class aimClass, Bundle bundle, int requestCode) {
        if (jumpInterface == null) return;
        Intent intent = new Intent();
        intent.setClass(context, aimClass);
        if (bundle != null) intent.putExtras(bundle);
        jumpInterface.startActivityForResult(intent, requestCode);
    }

}
