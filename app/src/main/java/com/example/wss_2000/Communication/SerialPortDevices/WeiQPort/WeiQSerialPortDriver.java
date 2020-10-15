package com.example.wss_2000.Communication.SerialPortDevices.WeiQPort;


import java.io.UnsupportedEncodingException;

import weiqian.hardware.SerialPort;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/22
 */
public class WeiQSerialPortDriver extends SerialPort {
    @Override
    public <T> T receiveData(T data) {
        byte[] buf = new byte[1024];
        int size = this.read(buf, buf.length);
        if (size > 0) {
            try {
                if (data instanceof byte[]) {
                    byte[] e = new byte[size];
                    System.arraycopy(buf, 0, e, 0, size);
                    data = (T) e;
                } else {
                    data = (T) (new String(buf, 0, size, "gb2312")).toString();
                }
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }
            return data;
        } else {
            return null;
        }
    }
}
