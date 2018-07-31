package com.dbgreat.demo.activemq.Service;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void sendMsg(String data, Destination destination) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        System.out.println("发送RealQueue消息,发送时间" + simpleDateFormat.format(new Date()));
        this.jmsMessagingTemplate.convertAndSend(destination, data);
    }

    @Override
    public void sendDelayMsg(String data, String queueName, Long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        //获取连接工厂
        ConnectionFactory connectionFactory = this.jmsMessagingTemplate.getConnectionFactory();
        if (connectionFactory == null) {
            return;
        }
        try {
            //获取连接
            try (Connection connection = connectionFactory.createConnection()) {
                connection.start();
                //获取session
                try (Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE)) {
                    // 创建一个消息队列
                    Destination destination = session.createQueue(queueName);
                    try (MessageProducer producer = session.createProducer(destination)) {
                        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
                        TextMessage message = session.createTextMessage(data);
                        //设置延迟时间
                        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
                        //发送
                        producer.send(message);
                        System.out.println("发送DelayQueue消息,发送时间" + simpleDateFormat.format(new Date()));
                        session.commit();
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
