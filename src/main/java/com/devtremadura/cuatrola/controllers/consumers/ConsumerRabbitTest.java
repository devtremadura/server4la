package com.devtremadura.cuatrola.controllers.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ConsumerRabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "hello", concurrency = "10")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        rabbitTemplate.convertAndSend("hello", "asdfasdfasdf");

    }

    public void publishMessage() {

    }

}
