package com.dbgreat.demo.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

@SpringBootApplication
public class ActivemqApplication {

	@Bean
	public Queue queue1() {
		return new ActiveMQQueue("queue1");
	}

	public static void main(String[] args) {
		SpringApplication.run(ActivemqApplication.class, args);
	}
}
