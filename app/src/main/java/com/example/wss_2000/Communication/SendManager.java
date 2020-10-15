package com.example.wss_2000.Communication;

import android.util.Log;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/26
 */
public class SendManager {

    public static void SendCmd(final String stringExtra, final Communication sp, final int reTry, final int sleep, final Object object) {
        Thread thr = new Thread(() -> Send(stringExtra, sp, reTry, sleep, object));
        thr.start();
    }

    private static void Send(String stringExtra, final Communication sp, int reTry, int sleep, Object object) {
        try {
            if (sp == null)
                return;
            synchronized (sp)
            {
                SendThread thread = new SendThread(stringExtra, sp, reTry, sleep, object);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.e("exception", "send" + e.toString());
        }
    }
}
