
package com.devtremadura.cuatrola.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtremadura.cuatrola.models.Hello;
import com.devtremadura.cuatrola.services.IHelloWorldSerivce;

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

    @GetMapping("/hello")
    public String hello() {

        rabbitTemplate.convertAndSend("hello", Hello.builder().message("asdfasdfasd").build());

        return helloWorldService.getHelloWorldMessage();
    }

}