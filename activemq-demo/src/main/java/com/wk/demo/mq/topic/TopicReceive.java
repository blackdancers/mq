package com.wk.demo.mq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * 接收订阅
 * Created by liudebin@99114.com on 2016/8/5.
 */
public class TopicReceive {
    private MessageConsumer consumer;
    private Session session;

    public void init() throws JMSException {
        /**
         * 连接工厂，JMS用它创建连接
         */
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.1.130:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("MessageTopic");
        consumer = session.createConsumer(topic);
        /**
         * 监听注册到需要订阅的topic上
         */
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage txMessage = (TextMessage) message;
                System.out.println("txMessage --> "+txMessage);
                try {
                    System.out.println("text --> "+txMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Test
    public void receiveSub() throws JMSException {
        TopicReceive topicReceive = new TopicReceive();
        topicReceive.init();
    }
}
