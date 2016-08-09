package com.wk.spring.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听器
 * MessageListener是最原始的消息监听器，它是JMS规范中定义的一个接口。其中定义了一个用于处理接收到的消息的onMessage方法，该方法只接收一个Message参数。
 * Created by liudebin@99114.com on 2016/8/9.
 */
public class ConsumerMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage txMessage = (TextMessage) message;
        try {
            String text = txMessage.getText();
            System.out.println("1、收到一条信息:"+text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
