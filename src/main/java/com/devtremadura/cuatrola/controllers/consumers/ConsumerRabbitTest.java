package com.devtremadura.cuatrola.controllers.consumers;

import com.devtremadura.cuatrola.domain.PlayedHand;
import com.devtremadura.cuatrola.services.PlayedHandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Random;
import java.util.UUID;

@Controller
public class ConsumerRabbitTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerRabbitTest.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    PlayedHandService playedHandService;

    @RabbitListener(queues = "hello", concurrency = "10")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        PlayedHand p = PlayedHand.builder().id(new Random().nextLong())
                .handName("HAND TEST ".concat(String.valueOf(UUID.randomUUID()))).build();
        PlayedHand saved = playedHandService.savePlayedHand(p);
        LOGGER.info("SAVED HAND {}", saved.getHandName());
        // rabbitTemplate.convertAndSend("hello", p);
    }

}
