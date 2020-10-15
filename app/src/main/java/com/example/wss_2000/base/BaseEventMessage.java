package com.example.wss_2000.base;

import android.os.Message;

/**
 * EventMessage 基础类
 */
public class BaseEventMessage {

    private String sCode; // code为事件码，使用的时候给不同的事件类型指定不同的code。

    private Message msg;// 事件内容

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }

}
