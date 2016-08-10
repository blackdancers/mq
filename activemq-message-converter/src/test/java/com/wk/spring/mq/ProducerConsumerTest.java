package com.wk.spring.mq;

import com.wk.spring.mq.message.EmailMessage;
import com.wk.spring.mq.service.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import java.util.ArrayList;
import java.util.List;

/**
 *  @Author Tyue 
 *  @Email  blackdancer.xm@gmail.com
 *  @Date   2016/8/10 14:52
 *  @Desc   测试 消息转换器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class ProducerConsumerTest {
    @Autowired
    private ProducerService producerService;
    @Autowired
    @Qualifier("messageConverterQueue")
    private Destination destination;
    @Test
    public void testSend(){
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom("blackdancers@163.com");
        List<String> addrs = new ArrayList<>();
        addrs.add("liudebin@99114.com");
        addrs.add("blackdancer.xm@gmail.com");
        emailMessage.setMailTo(addrs);
        emailMessage.setSubject("测试消息转换器");
        emailMessage.setContent("哈哈哈哈哈哈哈");
        producerService.sendMessage(destination,emailMessage);
    }
}
