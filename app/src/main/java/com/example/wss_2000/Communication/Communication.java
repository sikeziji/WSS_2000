package com.example.wss_2000.Communication;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 */
public abstract class Communication {

    /**
     * @param data 数据
     * @param <T>
     */
    public abstract <T> void sendData(T data);

    /**
     * @param data 数据
     * @param <T> 数据容器
     * @return  数据内容
     */
    public abstract <T> T receiveData(T data);

    /**
     * 通讯口关闭
     */
    public abstract void close();

    /**
     * @return true:关闭，flase：开启
     */
    public abstract boolean isClose();
    /**
     * @return 是否同步接受数据
     */
    public abstract boolean isSynchronous();

    /**
     * @param synchronous 同步接受
     */
    public abstract void setSynchronous(boolean synchronous);

    /** 是否接收到数据
     * @return
     */
    public abstract boolean isReceiveData() ;


    /**
     * @param receiveData 设置是否接收到数据
     */
    public abstract void setReceiveData(boolean receiveData) ;

    /**
     * @param str 数据描述
     * @param port 通讯口
     * @param rs 数据内容
     */
    public abstract boolean receiveParsing(String str, Communication port, byte[]rs);



    public enum Port {
        WeiQPort,
        angChuangPort,
        SerialPortMC,
        YangChuangPort,
        TCP,
        zTEKPort
    }
}
