package com.wk.spring.mq.service.impl;

import com.wk.spring.mq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 *
 * MessageListenerAdapter除了会自动的把一个普通Java类当做MessageListener来处理接收到的消息之外，
 *   其另外一个主要的功能是可以自动的发送返回消息。
 * Created by liudebin@99114.com on 2016/8/8.
 */
@Service("producerService")
public class ProducerServiceImpl implements ProducerService {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 在发送消息之前先指定该消息对应的回复目的地为一个叫responseQueue的队列目的地
     */
    @Autowired
    @Qualifier("responseQueue")
    private Destination responseDestination;


    /**
     * 通过jmsTemplate来发送消息到对应的Destination的.
     * @描述 到此，我们生成一个简单的文本消息并把它发送到指定目的地Destination的生产者就配置好了
     * @param destination
     * @param message
     */
    @Override
    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：\n" + message);
        this.jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                //指定该消息对应的回复消息的目的地
                textMessage.setJMSReplyTo(responseDestination);
                return textMessage;
            }
        });

    }
}
