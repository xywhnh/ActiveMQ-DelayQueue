package com.dbgreat.demo.activemq.Service;

import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 *
 */
@Service
public interface ProducerService {

    /**
     * 发送即时消息
     * @param data
     */
    void sendMsg(String data,Destination destination);

    void sendDelayMsg(String data,String queueName,Long time);
}
