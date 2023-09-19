package com.devtremadura.cuatrola.controllers.consumers;

import com.devtremadura.cuatrola.domain.PlayedHand;
import com.devtremadura.cuatrola.services.PlayedHandService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ConsumerRabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    PlayedHandService playedHandService;

    @RabbitListener(queues = "hello", concurrency = "10")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        PlayedHand p = PlayedHand.builder().key("TEST-".concat(String.valueOf(UUID.randomUUID()))).handName("HAND TEST ".concat(String.valueOf(UUID.randomUUID()))).build();
        playedHandService.savePlayedHand(p);

        rabbitTemplate.convertAndSend("hello", p);
    }

}
