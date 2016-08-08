package com.wk.demo.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理
 * Created by liudebin@99114.com on 2016/8/5.
 */
public class RequestProcessor {


    public void requestHandler(HashMap<Serializable, Serializable> requestParam) {
        System.out.println("requestHandler...." + requestParam.toString());
        for (Map.Entry entry : requestParam.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
    @Test
    public void process() throws JMSException {
        ConnectionFactory connectionFactory=
                new ActiveMQConnectionFactory( ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,
                        "tcp://192.168.1.130:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("RequestQueue");
        /**
         * 消费者（接收）
         */
        MessageConsumer consumer = session.createConsumer(destination);
        RequestProcessor processor = new RequestProcessor();
        while (true){
            ObjectMessage message = (ObjectMessage) consumer.receive(1000);
            if (null != message){
                System.out.println(message);
                HashMap<Serializable, Serializable> requestParam= (HashMap<Serializable, Serializable>) message.getObject();
                processor.requestHandler(requestParam);
            }else {
                break;
            }
        }
    }
}














