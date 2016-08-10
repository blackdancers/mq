package com.wk.spring.mq.listener;

/**
 *  普通的Java类
 * Created by liudebin@99114.com on 2016/8/9.
 */
public class ConsumerMessageListener {
    /**
     * 默认调用此方法(Spring会默认调用目标处理器的handleMessage方法)
     * @param message
     */
    public void handleMessage(String message){
        System.out.println("ConsumerMessageListener handleMessage:"+message);
    }

    /**
     *  当返回类型是非null时MessageListenerAdapter会自动把返回值封装成一个Message，然后进行回复
     * @param message
     */
    public String receiveMessage(String message){
        System.out.println("ConsumerMessageListener receiveMessage:"+message);
        return "这是ConsumerListener对象的receiveMessage方法的返回值。";
    }
}
