package com.wk.spring.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by liudebin@99114.com on 2016/8/8.
 */
public class ConsumerMessageListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("消息内容是:"+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
