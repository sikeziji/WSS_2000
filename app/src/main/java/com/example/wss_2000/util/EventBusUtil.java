package com.example.wss_2000.util;

import android.os.Message;

import com.example.wss_2000.base.BaseEventMessage;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtil {

    /**
     * 注册
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 注销
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    /**
     * 发送事件
     *
     * @param event 事件消息
     */
    public static void sendEvent(BaseEventMessage event) {
        EventBus.getDefault().post(event);
    }

    /**
     * 发送粘性事件
     *
     * @param event 事件消息
     */
    public static void sendStickyEvent(BaseEventMessage event) {
        EventBus.getDefault().postSticky(event);
    }


    /**
     * @param msgWho 时间发送地
     * @param iWhat  msg what
     * @param iArg1  msg arg1
     * @param iArg2  msg arg2
     * @param object msg obj
     */
    public static void setEventBusMessage(String msgWho, int iWhat, int iArg1, int iArg2, Object object) {
        Message msg = new Message();
        msg.what = iWhat;
        msg.arg1 = iArg1;
        msg.arg2 = iArg2;
        msg.obj = object;
        BaseEventMessage message = new BaseEventMessage();
        message.setMsg(msg);
        message.setsCode(msgWho);
        sendEvent(message);
    }

}
