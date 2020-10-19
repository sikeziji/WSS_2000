package com.example.wss_2000.Communication.TCP;


import android.util.Log;

import com.example.wss_2000.Communication.Communication;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 */
public class TcpPort extends Communication {

    private SocketConnect myTcp;
    private boolean portEnable;     // 使能
    private boolean isSynchronous; // 同步接收
    private boolean isReceiveData; // 接收数据标志位


    public TcpPort(String ip, int port, boolean synchronous) {
        try {
            myTcp = new SocketConnect(ip, port);
            setSynchronous(synchronous);
            portEnable = true;
        } catch (Exception e) {
            Log.e("TcpPort", e.toString());
        }
    }

    /**
     * 发送数据
     *
     * @param data 数据
     * @param <T>
     */
    @Override
    public <T> void sendData(T data) {
        setReceiveData(false);
        myTcp.sendData(data);
    }

    /**
     * 数据接收
     *
     * @param data 数据
     * @param <T>
     * @return
     */
    @Override
    public <T> T receiveData(T data) {
        return myTcp.Receive(data);
    }

    /**
     * TCP关闭
     */
    public void close() {
        myTcp.disconnect();
        myTcp = null;
        portEnable = false;
    }

    /**
     * 判定TCP 是否主动关闭
     *
     * @return
     */
    @Override
    public boolean isClose() {
        return portEnable;
    }

    /**
     * 数据是否同比接收
     *
     * @return
     */
    @Override
    public boolean isSynchronous() {
        return isSynchronous;
    }

    /**
     * @param synchronous 同步接受
     */
    @Override
    public void setSynchronous(boolean synchronous) {
        this.isSynchronous = synchronous;
    }

    /**
     * 是否接收到数据
     *
     * @return
     */
    @Override
    public boolean isReceiveData() {
        return isReceiveData;
    }

    /**
     * @param receiveData 设置是否接收到数据
     */
    @Override
    public void setReceiveData(boolean receiveData) {
        this.isReceiveData = receiveData;
    }

    /**
     * @param str  数据描述
     * @param port 通讯口
     * @param rs   数据内容
     * @return
     */
    @Override
    public boolean receiveParsing(String str, Communication port, byte[] rs) {
       /*TODO TCPPort数据描述
        setReceiveData(true);
        int iTcpProtocol=0;
        int iMsg=5;
        if (TCP1.equals(port)) {
            iTcpProtocol = Integer.parseInt(getPublicConfigData("TCP_1_Protocol"));
            iMsg = 5;
        } else if (TCP2.equals(port)) {
            iTcpProtocol = Integer.parseInt(getPublicConfigData("TCP_2_Protocol"));
            iMsg = 6;
        } else if (TCP3.equals(port)) {
            iTcpProtocol = Integer.parseInt(getPublicConfigData("TCP_3_Protocol"));
            iMsg = 7;
        }
        ProtocolResponse protocolResponse = new ProtocolResponse();
        protocolResponse.ParsingProtocol(port, 0, protocolList[iTcpProtocol], rs);

        try {
            if (digitalTest) {
                setEventBusMessage("Left4_Content2", iMsg, 0, 0, rs);
            }
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }*/
        return false;
    }

}
