package com.dbgreat.demo.activemq.Service;

import com.dbgreat.demo.activemq.Common.IConsumer;
import com.dbgreat.demo.activemq.Common.ObjectLoader;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @JmsListener(destination = "RealQueue")
    public void receiveRealQueueMsg(String data) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        System.out.println("接收到RealQueue的数据:" + data + " ,接收时间 :" + simpleDateFormat.format(new Date()));
    }

//    @JmsListener(destination = "DelayQueue")
//    public void receiveDelayQueueMsg(String data) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
//        System.out.println("接收到DelaQueue的数据:" + data + " ,接收时间 :" + simpleDateFormat.format(new Date()));
//    }

    @JmsListener(destination = "DelayQueue")
    public void onMessage() {
        System.out.println("开始执行订阅器");
        Map<String, Object> map = ObjectLoader.getInstances();
        if (!CollectionUtils.isEmpty(map)) {
            System.out.println("发现" + map.size() + "个订阅器");
            for (Map.Entry entry : map.entrySet()) {
                try {
                    IConsumer consumer = (IConsumer) entry.getValue();
                    consumer.execute();
                } catch (Exception e) {
                    System.out.println("订阅器执行失败," + e);
                }
            }
        }
    }

    @Override
    public void addConsumer(String classPath) {
        ObjectLoader.addInstance(classPath);
    }

}
