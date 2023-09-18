package com.devtremadura.cuatrola.services.impl;

import org.springframework.stereotype.Service;

import com.devtremadura.cuatrola.services.IHelloWorldSerivce;

@Service
public class HelloWorldServiceImpl implements IHelloWorldSerivce {

    @Override
    public String getHelloWorldMessage() {
        return "Hello world from service";
    }

}
