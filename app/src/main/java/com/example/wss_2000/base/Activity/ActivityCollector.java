package com.example.wss_2000.base.Activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    private static List<Activity> activities = new ArrayList<Activity>();

    /**
     * 添加Activity
     * @param activity 添加的Activity对象
     * */
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 删除Activity
     * @param activity 删除的Activity对象
     * */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public static void finishActivity() {
        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
