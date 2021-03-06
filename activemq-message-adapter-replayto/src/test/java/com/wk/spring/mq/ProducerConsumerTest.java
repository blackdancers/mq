package com.wk.spring.mq;

import com.wk.spring.mq.service.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * Created by liudebin@99114.com on 2016/8/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProducerConsumerTest {
    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("adapterQueue")
    private Destination destination;

    @Test
    public void testSend(){
        producerService.sendMessage(destination,"测试MessageListenerAdapter");
    }
}
