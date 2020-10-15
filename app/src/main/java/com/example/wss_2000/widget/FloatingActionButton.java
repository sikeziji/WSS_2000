package com.example.wss_2000.widget;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.Iterator;


public class FloatingActionButton {

    public static Button getFloatingActionButton(Context context, int resourceId, @Nullable View.OnClickListener mfbClick) {

        Button mfb = new Button(context);
        mfb.setBackgroundResource(resourceId);
        mfb.setOnClickListener(mfbClick);
        return mfb;
    }

    public static void initView(Context sContext, WindowManager windowManager, final View childView, float x, float width, float height) {
        final WindowManager wm = windowManager;
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = 2010;
        params.flags = 40;
        params.format = 1;
        params.gravity = 53;
        params.x = (int) x;
        params.width = DensityUtil.px_x((float) ScreenUtils.getScreenWidth(sContext), (float) width);
        params.height = DensityUtil.px_y((float) ScreenUtils.getRealScreenHeight(sContext), (float) height);
        // 8.0系统加强后台管理，禁止在其他应用和窗口弹提醒弹窗，如果要弹，必须使用TYPE_APPLICATION_OVERLAY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
        try {
            wm.addView(childView, params);
        } catch (Exception var7) {
        }

        childView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                params.x = wm.getDefaultDisplay().getWidth() - (int) motionEvent.getRawX() - childView.getWidth() / 2;
                params.y = (int) motionEvent.getRawY() - childView.getHeight() / 2;
                wm.updateViewLayout(childView, params);
                return false;
            }
        });
    }

    public static void fixAPx(Context sContext, Window window) {
        Iterator var1 = DensityUtil.getAllChildViews(window.getDecorView()).iterator();

        while (var1.hasNext()) {
            View item = (View) var1.next();
            DensityUtil.fixViewPx(sContext, item);
        }

    }

    public static void fixAPx(Context sContext, View v) {
        DensityUtil.fixPx(sContext, v);
    }

    public static void removeFloatingActionButton(View childView, WindowManager windowManager) {
        WindowManager wm = windowManager;

        try {
            wm.removeView(childView);
        } catch (Exception var4) {
        }

    }

}
