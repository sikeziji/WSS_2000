package com.zt.base.AppBase.DualProcessDaemon;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

/*双进程守护***/
public class DualProcessDaemon {

    private static Class<?> mainActivity = null;
    private static boolean blStarted = false;

    /**
     * @param context Application启动服务
     */
    public static void start(Context context) {
        if (isMainProcess(context)) {
            context.startService(new Intent(context, LocalService.class));
            blStarted = true;
        }
    }


    /*双进程守护***/
    static Class<?> getMainActivity() {
        return mainActivity;
    }

    /**
     * 设置启动activity
     *
     * @param startActivity mainActivity
     */
    public static void init(Class<?> startActivity) {
        if (blStarted) {
            mainActivity = startActivity;
        }
    }


    /**
     * 获取当前进程名获取当前进程名
     *
     * @param context       appContext
     * @return  进程名
     */
    private static String getCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService
                (Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
                if (process.pid == pid) {
                    processName = process.processName;
                }
            }
        }
        return processName;
    }

    /**
     * 是否为主进程
     */
    private static boolean isMainProcess(Context context) {

        boolean isMainProcess;
        isMainProcess = context.getApplicationContext().getPackageName().equals
                (getCurrentProcessName(context));
        return isMainProcess;
    }

    /**
     * 停止两个监控进程
     *
     * @param context app
     */
    public static void stop(Context context) {
        if (blStarted) {
            context.stopService(new Intent(context, LocalService.class));
            context.stopService(new Intent(context, RemoteService.class));
        }
    }

}
