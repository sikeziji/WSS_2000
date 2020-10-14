package com.example.wss_2000.util;

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

}
