package com.example.wss_2000.Communication;

import android.os.Environment;


import com.example.wss_2000.MyApplication;
import com.example.wss_2000.util.FileUtils;
import com.example.wss_2000.util.PackageUtils;

import java.io.File;

import static java.lang.System.arraycopy;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/26
 */
public class SendThread extends Thread {
    private Communication sp;
    private String commandDescribe;
    private Object mObject;
    private int sendTry;
    private int sleep;
    private static String sAppName;
    private static String sFileRunningLogPath;
    private String Component;
    private String Command;
    private byte Number;
    private String Code;


    SendThread(String commandDescribe, Communication sp, int Try, int sleep, Object object) {
        mObject = object;
        this.commandDescribe = commandDescribe;
        Component = commandDescribe.split("_")[0];
        Command = commandDescribe.split("_")[1];
        Number = Byte.valueOf(commandDescribe.split("_")[2], 16);
        Code = commandDescribe.split("_")[3];
        this.sp = sp;
        this.sendTry = Try;
        this.sleep = sleep;
        if (sAppName == null) {
            sAppName = PackageUtils.getAppName(MyApplication.getMyAppContext());
            sFileRunningLogPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + sAppName + "/communication/";
        }

    }


    @Override
    public void run() {
        /*TODO SendThread
        try {
            int iTry;
            byte[] rs = new byte[0];
            byte[] cmd = new byte[0];

            if (sp != null) {
                for (iTry = 0; iTry < sendTry; iTry++) {
                    if (S1 != null && S1.equals(sp)) {
                        cmd = getBoardCmd(Number, Code, mObject);
                    } else if (S2 != null && S2.equals(sp)) {
                        cmd = getCmd(Component, Command, Number, Code, mObject);
                    } else if (S3 != null && S3.equals(sp)) {
                        cmd = (byte[]) mObject;
                    } else if (S4 != null && S4.equals(sp)) {
                        cmd = (byte[]) mObject;
                    }
                    if (sp != null) {
                        sp.sendData(cmd);
                    }
                    if (sp != null) {
                        if (sp.isSynchronous()) {
                            // 同步需要接收数据
                            rs = getPortData();
                            if (rs != null && rs.length > 1) {
                                // 数据解析
                                if (sp != null && sp.receiveParsing(commandDescribe, sp, rs)) {
                                    break;
                                }
                            }
                            if (sendTry != 1) {
                                if (iTry == (sendTry - 1)) {
                                    // 未收到正确数据记录异常；
                                    FileUtils.saveCommunicationRunInfoToFile(sFileRunningLogPath, "发送" + sendTry + "次[" + commandDescribe + "]" + "超时，通讯失败..." + "\r\n" + bytesToHexString(cmd, cmd.length));
                                    break;
                                }
                            }
                        } else {
                            Thread.sleep(sleep);
                            if (sp.isReceiveData()) {
                                // 接收到数据
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 获取串口数据
     *
     * @return 接收到数据
     */
    private byte[] getPortData() {
        byte[] byRs;
        byte[] reData = new byte[4000];
        int len = 0;
        int timeOut = 0;
        byte[] rs = new byte[0];

        while (sp != null) {
            byRs = new byte[0];
            byRs = sp.receiveData(byRs);
            if (byRs != null) {
                if (byRs.length > 0) {
                    if (byRs.length < reData.length && byRs.length < (reData.length - len)) {
                        arraycopy(byRs, 0, reData, len, byRs.length);
                        len += byRs.length;
                    }
                }
            } else {
                if (len > 0) {
                    rs = new byte[len];
                    arraycopy(reData, 0, rs, 0, len);
                    break;
                }
                len = 0;
            }
            try {
                Thread.sleep(12);
                if ((++timeOut * 12) > sleep) {
                    rs = null;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
