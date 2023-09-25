
package com.devtremadura.cuatrola.controllers;

import com.devtremadura.cuatrola.domain.Player;
import com.devtremadura.cuatrola.domain.User;
import com.devtremadura.cuatrola.services.PlayerService;
import com.devtremadura.cuatrola.services.UserService;

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
    private PlayerService playerService;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {

        // rabbitTemplate.convertAndSend("hello",
        // Hello.builder().message("asdfasdfasd").build());

        User u = User.builder()
                .firstname("Test")
                .surname("Test")
                .nickname("@test")
                .build();

        userService.saveUser(u);

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