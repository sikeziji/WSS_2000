package com.example.wss_2000.widget;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wss_2000.util.ResourceUtil;

import java.util.List;


/**
 * 项目名称    BaseProject
 * 类描述      封装常用控件接口
 * 创建人      hp
 * 创建时间    2019/12/16
 */
public class Widget {


    /**
     * 初始化下拉框
     *
     * @param context  上下文
     * @param spinner  控件名称
     * @param arrList  数据资源id
     * @param OutStyle 风格
     * @param InStyle  风格
     */
    public static void InitSpinner(Context context, Spinner spinner, int arrList, int OutStyle, int InStyle) {
        String[] level = ResourceUtil.getStringArray(arrList);
        InitSpinner(context, spinner, level, OutStyle, InStyle);
    }

    /**
     * 初始化下拉框
     *
     * @param context  上下文
     * @param spinner  控件名称
     * @param arrList  数据list
     * @param OutStyle 风格
     * @param InStyle  风格
     */
    public static void InitSpinner(Context context, Spinner spinner, String[] arrList, int OutStyle, int InStyle) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, OutStyle);
        for (String s : arrList) {
            adapter.add(s);
        }
        adapter.setDropDownViewResource(InStyle);
        spinner.setAdapter(adapter);
    }

    /**
     * @param context    上下文
     * @param spinner    控件名称
     * @param arrList    数据list
     * @param OutStyle   风格
     * @param InStyle    风格
     * @param lstNotShow 不显示的
     */
    public static void InitSpinner(Context context, Spinner spinner, String[] arrList, int OutStyle, int InStyle, List<String> lstNotShow) {
        ArrayAdapter<String> adapter = new ArrayAdapter(context, OutStyle);
        String[] level = arrList;

        for (int i = 0; i < level.length; ++i) {
            if (!lstNotShow.contains(level[i])) {
                adapter.add(level[i]);
            }
        }

        adapter.setDropDownViewResource(InStyle);
        spinner.setAdapter(adapter);
    }


    /**
     * @param onListener Edit 监听事间实现
     * @param views      控件
     */
    public static void setOnEditorActionListener(Object onListener, View... views) {
        for (View view : views) {
            view.setOnFocusChangeListener((View.OnFocusChangeListener) onListener);
            ((TextView) (view)).setOnEditorActionListener((TextView.OnEditorActionListener) onListener);
        }
    }
}
