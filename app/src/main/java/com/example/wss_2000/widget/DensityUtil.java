package com.example.wss_2000.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DensityUtil {
    public DensityUtil() {
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(2, spVal, context.getResources().getDisplayMetrics());
    }

    public static float px2sp(Context context, float pxVal) {
        return pxVal / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int px_x(float ScreenWidth, float px) {
        //return px != -1.0F && px != -2.0F ? (int)(px /2160.0F /*1024.0F*/ * ScreenWidth) : (int)px;
        return (int) px;
    }

    public static int px_y(float ScreenHeight, float px) {
        //return px != -1.0F && px != -2.0F ? (int)(px / 1080.0F/*600.0F*/ * ScreenHeight) : (int)px;
        return (int) px;
    }

    public static List<View> getAllChildViews(View view) {
        List<View> allchildren = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup)view;

            for(int i = 0; i < vp.getChildCount(); ++i) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }

        return allchildren;
    }

    public static void fixViewPx(Context context, View v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        layoutParams.height = px_y((float)ScreenUtils.getRealScreenHeight(context), (float)layoutParams.height);
        layoutParams.width = px_x((float)ScreenUtils.getScreenWidth(context), (float)layoutParams.width);
        double ppi = Math.sqrt(1408576.0D) / 7.0D;
        v.setLayoutParams(layoutParams);
        setMargins(context, v);
        v.setPadding(px_x((float)ScreenUtils.getScreenWidth(context), (float)v.getPaddingLeft()), px_y((float)ScreenUtils.getRealScreenHeight(context), (float)v.getPaddingTop()), px_x((float)ScreenUtils.getScreenWidth(context), (float)v.getPaddingRight()), px_y((float)ScreenUtils.getRealScreenHeight(context), (float)v.getPaddingBottom()));
    }

    private static int changeTextSize(Context context, int size) {
        Resources r = context.getResources();
        return (int)TypedValue.applyDimension(2, (float)size, r.getDisplayMetrics());
    }

    public static void setMargins(Context context, View v) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
            p.setMargins(px_x((float)ScreenUtils.getScreenWidth(context), (float)p.leftMargin), px_y((float)ScreenUtils.getRealScreenHeight(context), (float)p.topMargin), px_x((float)ScreenUtils.getScreenWidth(context), (float)p.rightMargin), px_y((float)ScreenUtils.getRealScreenHeight(context), (float)p.bottomMargin));
            v.requestLayout();
        }

    }

    public static void fixPx(Context context,View v) {
        fixViewPx(context, v);
        Iterator var1 = getAllChildViews(v).iterator();

        while(var1.hasNext()) {
            View item = (View)var1.next();
            fixViewPx(context, item);
        }

    }
}
