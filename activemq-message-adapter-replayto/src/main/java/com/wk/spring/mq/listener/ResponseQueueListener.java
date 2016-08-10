package com.wk.spring.mq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * responseQueue对应的监听器
 * Created by liudebin@99114.com on 2016/8/10.
 */
public class ResponseQueueListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("-----------\n接收到发送到responseQueue的一个文本消息，内容是："+textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
