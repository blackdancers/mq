package com.wk.spring.mq.service;

import javax.jms.Destination;

/**
 * Created by liudebin@99114.com on 2016/8/8.
 */
public interface ProducerService {
    void sendMessage(Destination destination,final String message);
}
