package com.wk.demo.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 通过Java实现基于ActiveMQ的请求
 * Created by liudebin@99114.com on 2016/8/5.
 */
public class RequestSubmit {
    /**
     * 消息发送者
     */
    private MessageProducer producer;
    /**
     * 一个发送或者接收信息的线程
     */
    private Session session;

    public void init() throws JMSException {
        /**
         * 连接工厂，JMS用它创建连接
         */
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.1.130:61616");
        /**
         * 从工厂中获得连接
         */
        Connection connection = connectionFactory.createConnection();
        /**
         * 启动连接
         */
        connection.start();
        /**
         * 获取连接操作
         * AUTO_ACKNOWLEDGE:自动应答模式
         */
        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        /**
         * 目标
         */
        Destination destination = session.createQueue("RequestQueue");
        /**
         * 得到消息生成（发送）者
         */
        producer = session.createProducer(destination);
        /**
         * 设置不持久化
         */
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        /**
         *
         */

    }

    public void submit(HashMap<Serializable,Serializable> requestParam) throws JMSException {
        ObjectMessage message = session.createObjectMessage(requestParam);
        producer.send(message);
        session.commit();
    }

    @Test
    public void demo() throws JMSException {
        RequestSubmit submit =new RequestSubmit();
        submit.init();
        HashMap<Serializable,Serializable> requestParam = new HashMap<Serializable, Serializable>();
        requestParam.put("tyue","test message!!");
        submit.submit(requestParam);
    }



}
