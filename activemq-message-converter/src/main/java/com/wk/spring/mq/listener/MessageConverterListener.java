package com.wk.spring.mq.listener;

import com.wk.spring.mq.message.EmailMessage;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *  @Author Tyue 
 *  @Email  blackdancer.xm@gmail.com
 *  @Date   2016/8/10 15:02
 *  @Desc   
 */
public class MessageConverterListener implements MessageListener {

    private MessageConverter messageConverter;

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                //1、把Java对象转换成对应的Jms Message
//                Object object = objectMessage.getObject();
//                EmailMessage emailMessage = (EmailMessage) object;
//                System.out.println("接收到一个ObjectMessage消息，包含EmialMessage对象:---\n"+emailMessage);

                //2、把Jms Message转换成对应的Java对象
                EmailMessage emailMessage = (EmailMessage) messageConverter.fromMessage(objectMessage);
                System.out.println("接收到一个ObjectMessage消息，包含EmialMessage对象:---"+emailMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
