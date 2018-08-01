package com.dbgreat.demo.activemq;

import com.dbgreat.demo.activemq.Common.IConsumer;
import org.springframework.stereotype.Component;

@Component
public class Consumer1 implements IConsumer {
    @Override
    public void execute() {
        System.out.println("Consumer 1 execute");
    }
}
