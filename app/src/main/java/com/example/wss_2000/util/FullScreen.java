package com.example.wss_2000.util;

import android.content.Context;
import android.content.Intent;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.util
 * @ClassName: FullScreen
 * @Description: 全屏操作
 * @Author: wangj
 * @CreateDate: 2020/10/15 8:48
 * @Version: 1.0
 */
public class FullScreen {

    /*
     * 广播全屏操作
     */
    public static void broadCastFullScreen(String phoneName, boolean isShow, Context context) {
        switch (getSystemSupportDevices(phoneName)) {
            case 3: {
                if (!isShow) {
                    //设置广播发送隐藏虚拟按键命令
                    try {
                        String command;
                        command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib service call activity 42 s16 com.android.systemui";
                        Process proc = Runtime.getRuntime().exec(new String[]{"su",
                                "-c", command});
                        proc.waitFor();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    //设置广播发送显示虚拟按键命令
                    try {
                        String command;
                        command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
                        Process proc = Runtime.getRuntime().exec(new String[]{"su",
                                "-c", command});
                        proc.waitFor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case 1: {
//                if (!isShow) {
//                    //设置广播发送隐藏虚拟按键命令
//                    YcApi.newInstance().HideNviBarFull();
//                } else {
//                    //设置广播发送显示虚拟按键命令
//                    YcApi.newInstance().ShowNviBarFull();
//                }
            }
            break;
            default:
                setNavigationBar(!isShow, context);
                break;
        }

    }

    private static int getSystemSupportDevices(String phone) {
        switch (phone) {
            case "rk3288":
                return 1;
            case "AOSP on weiqian":
                return 2;
            case "QUAD-CORE A33 y3":
                return 3;
            default:
                return 0;
        }
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
}
