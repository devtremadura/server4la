
package com.devtremadura.cuatrola.controllers;

import com.devtremadura.cuatrola.domain.Player;
import com.devtremadura.cuatrola.domain.User;
import com.devtremadura.cuatrola.services.PlayerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtremadura.cuatrola.models.Hello;
import com.devtremadura.cuatrola.services.IHelloWorldSerivce;

import java.util.ArrayList;

/**
 * HelloWorldController
 */
@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @Autowired
    private IHelloWorldSerivce helloWorldService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/hello")
    public String hello() {

        //rabbitTemplate.convertAndSend("hello", Hello.builder().message("asdfasdfasd").build());

        User u = User.builder()
                .name("Test")
                .surname("Test")
                .nickname("@test")
                .build();

        Player p = Player.builder()
                .alone(false)
                .cuatrola(false)
                .shoutedForty(false)
                .shoutedTwenty(0)
                .user(u)
                .build();


        playerService.savePlayer(p);

        return helloWorldService.getHelloWorldMessage();
    }

}