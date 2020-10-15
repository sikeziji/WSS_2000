package com.example.wss_2000.Communication.TCP;


import com.example.wss_2000.Communication.Communication;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 */
public class InitTcpPortCommunicationThread {

    /**
     * 数据接收线程初始化
     *
     * @param port      串口
     * @param sleepTime 数据读取间隔时间 毫秒
     */
    public InitTcpPortCommunicationThread(Communication port, int sleepTime) {
        // 异步线程，只有当串口关闭的时候回退出线程；
        new Thread(new ReceiveTcpDataRunnable(port, sleepTime, new ReceiveTcpDataRunnable.Callback() {
            @Override
            public void receiveParse(Communication port, byte[] rs) {
                port.receiveParsing(null, port, rs);
            }
        })).start();
    }


}
