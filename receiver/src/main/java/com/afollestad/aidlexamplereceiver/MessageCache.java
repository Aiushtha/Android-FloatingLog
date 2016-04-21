package com.afollestad.aidlexamplereceiver;

import java.util.LinkedList;

import bean.MessageBean;

/**
 * Created by Lin on 16/4/18.
 */
public class MessageCache {
    public final static LinkedList<MessageBean> listMessages = new LinkedList<>();

    public static void addMessage(MessageBean bean) {
        if (listMessages.size() > 100) {
            listMessages.removeLast();
        }
        MessageCache.listMessages.addFirst(bean);
    }


}
