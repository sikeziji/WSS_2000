package com.example.wss_2000.Communication.SerialPort;

import com.example.wss_2000.Communication.Communication;

import static java.lang.System.arraycopy;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 */
public class ReceiveSerialPortDataRunnable implements Runnable {

    private Communication communicationPort;    // 串口
    private int spaceReadTime;                  // 串口读取间隔时间
    private Callback receiveParseCallBack;      // 数据接收解析回调

    /**
     * @param serialPort 串口
     * @param sleepTime  串口读取间隔时间单位 毫秒
     * @param callBack   数据接收解析回调
     */
    ReceiveSerialPortDataRunnable(Communication serialPort, int sleepTime, Callback callBack) {
        this.communicationPort = serialPort;
        this.spaceReadTime = sleepTime;
        this.receiveParseCallBack = callBack;
    }

    public interface Callback {
        void receiveParse(Communication port, byte[] rs);
    }

    @Override
    public void run() {
        byte[] rs;
        byte[] reData = new byte[4000];
        int len = 0;

        while (this.communicationPort != null) {
            // 串口是否关闭，串口是否异步接收
            if (this.communicationPort.isClose() && (!communicationPort.isSynchronous())) {
                return;
            }
            rs = new byte[0];
            rs = communicationPort.receiveData(rs);
            if (rs != null) {
                if (rs.length > 0) {
                    if (rs.length < reData.length && rs.length < (reData.length - len)) {
                        arraycopy(rs, 0, reData, len, rs.length);
                        len += rs.length;
                    }
                }
            } else {
                if (len > 0) {
                    byte[] tempData = new byte[len];
                    arraycopy(reData, 0, tempData, 0, len);
                    receiveParseCallBack.receiveParse(communicationPort, tempData);
                } else {
                    for (int j = 0; j < reData.length; j++) {
                        reData[j] = 0;
                    }
                }
                len = 0;
            }
            try {
                Thread.sleep(spaceReadTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
