package com.wk.spring.mq.service.impl;

import com.wk.spring.mq.service.ProducerService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.io.Serializable;

/**
 * @Author Tyue
 * @Email blackdancer.xm@gmail.com
 * @Date 2016/8/10 14:36
 * @Desc
 */
@Service("producerService")
public class ProducerServiceImpl implements ProducerService {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 没有使用 MessageConverter
     * @param destination
     * @param obj
     */
//    @Override
//    public void sendMessage(Destination destination, final Serializable obj) {
//        this.jmsTemplate.send(destination, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                ObjectMessage objectMessage = session.createObjectMessage(obj);
//                return objectMessage;
//            }
//        });
//    }

    /**
     * 使用MessageConverter的情况
     * @param destination
     * @param obj
     */
    @Override
    public void sendMessage(Destination destination, final Serializable obj) {
        this.jmsTemplate.convertAndSend(destination,obj);
    }

}
