package com.dbgreat.demo.activemq.Service;

import org.springframework.stereotype.Service;

@Service
public interface ConsumerService {
    void addListener();

    void processMsg();
}
