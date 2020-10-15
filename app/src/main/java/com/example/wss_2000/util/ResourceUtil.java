package com.example.wss_2000.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.wss_2000.MyApplication;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/5
 */
public class ResourceUtil {

    /**
     * 获取Resource对象
     */
    public static Resources getResources() {
        return MyApplication.getMyAppContext().getResources();
    }

    /**
     * 获取Drawable资源
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取字符串资源
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取color资源
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取dimens资源
     */
    public static float getDimens(int resId) {
        return getResources().getDimension(resId);
    }

    /**
     * 获取字符串数组资源
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

}
