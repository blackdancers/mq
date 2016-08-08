package com.wk.demo.mq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * 发布——订阅
 * Created by liudebin@99114.com on 2016/8/5.
 */
public class TopicRequest {
    /**
     * 消息发送者
     */
    private MessageProducer producer;
    /**
     * 一个发送或者接收信息的线程
     */
    private Session session;
    /**
     * JMS客户端到JMS Provider的连接
     */
    private Connection connection;

    public void init() throws JMSException {
        /**
         * 连接工厂，JMS用它创建连接
         */
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.1.130:61616");
        /**
         * 从工厂中获得连接
         */
        connection = connectionFactory.createConnection();
        /**
         * 启动连接
         */
        connection.start();
        /**
         * 获取连接操作
         * AUTO_ACKNOWLEDGE:自动应答模式
         */
        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("MessageTopic");

        producer = session.createProducer(topic);
        /**
         * 设置不持久化
         */
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    public void submit(String msg) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText(msg);
        producer.send(message);
    }

    public void close() {
        try {
            if (session != null) {
                session.close();
            }
            if (producer!=null){
                producer.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void publish() throws JMSException {
        TopicRequest topic = new TopicRequest();
        topic.init();
        topic.submit("哈哈，嘿嘿，呵呵");
        topic.close();
    }
}
