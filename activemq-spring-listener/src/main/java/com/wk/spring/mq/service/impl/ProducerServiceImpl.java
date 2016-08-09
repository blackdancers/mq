package com.wk.spring.mq.service.impl;

import com.wk.spring.mq.service.ProducerService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by liudebin@99114.com on 2016/8/8.
 */
@Service("producerService")
public class ProducerServiceImpl implements ProducerService {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 通过jmsTemplate来发送消息到对应的Destination的.
     * @描述 到此，我们生成一个简单的文本消息并把它发送到指定目的地Destination的生产者就配置好了
     * @param destination
     * @param message
     */
    @Override
    public void sendMessage(Destination destination, final String message) {
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        this.jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
