package com.dbgreat.demo.activemq.Service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ConsumerServiceImpl implements ConsumerService{
    @Override
    public void addListener() {

    }

    @Override
    public void processMsg() {

    }

    @JmsListener(destination = "RealQueue")
    public void receiveRealQueueMsg(String data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        System.out.println("接收到RealQueue的数据:" + data + " ,接收时间 :" + simpleDateFormat.format(new Date()));
    }

    @JmsListener(destination = "DelayQueue")
    public void receiveDelayQueueMsg(String data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        System.out.println("接收到DelaQueue的数据:" + data + " ,接收时间 :" + simpleDateFormat.format(new Date()));
    }
}
