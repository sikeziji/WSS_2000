package com.example.wss_2000.Communication.TCP;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/12/18
 */

import android.util.Log;

import com.example.wss_2000.Communication.Communication;


public class ReceiveTcpDataRunnable implements Runnable {

    private Communication tcpPort;
    private Callback callback;
    private int iSleepTime;

    public ReceiveTcpDataRunnable(Communication tcpPort, int iSleepTime, Callback callback) {
        this.tcpPort = tcpPort;
        this.iSleepTime = iSleepTime;
        this.callback = callback;
    }

    public interface Callback {
        void receiveParse(Communication port, byte[] tempData);

    }

    public void run() {
        byte[] rs;
        while (this.tcpPort != null) {
            try {
                rs = new byte[4096];
                rs = tcpPort.receiveData(rs);
                if (rs != null) {
                    callback.receiveParse(this.tcpPort, rs);
                }
                try {
                    Thread.sleep(iSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Log.e("exception ", "Tcp  ReceiveTcpDataRunnable  tcpPort" + e.toString());
            }
        }
    }
}