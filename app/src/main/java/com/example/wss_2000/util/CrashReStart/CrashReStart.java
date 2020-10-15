package com.example.wss_2000.util.CrashReStart;

import android.app.Activity;

import com.example.wss_2000.MyApplication;
import com.example.wss_2000.base.Activity.ActivityCollector;


public class CrashReStart {

    // 崩溃捕捉 初始化
    public static void init(MyApplication application, Activity startActivity) {
        //设置该CrashHandler为程序的默认处理器
        UnCeHandler catchException = new UnCeHandler(application, startActivity.getClass());
        Thread.setDefaultUncaughtExceptionHandler(catchException);
        ActivityCollector.addActivity(startActivity);
    }
}
