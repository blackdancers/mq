package com.wk.spring.mq.listener;

import com.wk.spring.mq.message.EmailMessage;

/**
 * @Author Tyue
 * @Email blackdancer.xm@gmail.com
 * @Date 2016/8/10 15:44
 * @Desc
 */
public class ConsumerListener {

    public void receiveMessage(String message) {
        System.out.println("ConsumerListener通过receiveMessage接收到一个纯文本消息，消息内容是：" + message);
    }

    public void receiveMessage(EmailMessage email) {
        System.out.println("接收到一个包含EmailMessage的ObjectMessage。");
        System.out.println(email);
    }
}
