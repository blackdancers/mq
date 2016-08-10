package com.wk.spring.mq.service;

import javax.jms.Destination;
import java.io.Serializable;

/**
 *  @Author Tyue 
 *  @Email  blackdancer.xm@gmail.com
 *  @Date   2016/8/10 14:34
 *  @Desc
 */
public interface ProducerService {

    void sendMessage(Destination destination,final Serializable obj);
}
