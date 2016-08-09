package com.wk.spring.mq.listener;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 *  SessionAwareMessageListener的设计就是为了方便我们在接收到消息后发送一个回复的消息
 * Created by liudebin@99114.com on 2016/8/9.
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    private Destination destination;

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        String text = message.getText();
        System.out.println("2、收到一条信息:"+text);
        MessageProducer producer = session.createProducer(destination);
        TextMessage msg = session.createTextMessage("...ConsumerSessionAwareMessageListener...");
        producer.send(msg);
    }
}
