package com.dbgreat.demo.activemq.controller;

import com.dbgreat.demo.activemq.Common.ObjectLoader;
import com.dbgreat.demo.activemq.Service.ConsumerService;
import com.dbgreat.demo.activemq.Service.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jms.Destination;

@Controller
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping("/delayMsg")
    public void send(@RequestParam(value = "time", defaultValue = "10000") Long time) {
        producerService.sendDelayMsg("延时消息", "DelayQueue", time);
    }

    @RequestMapping("/realMsg")
    public void send2() {
        Destination destination = new ActiveMQQueue("RealQueue");
        producerService.sendMsg("实时消息", destination);
    }

    @RequestMapping("/consumer")
    public void addConsumer(@RequestParam(value = "classPath") String classPath) {
        consumerService.addConsumer(classPath);
    }

    @RequestMapping("/consumerSize")
    public String getSize() {
        return String.valueOf(ObjectLoader.getSize());
    }
}
