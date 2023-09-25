package com.devtremadura.cuatrola.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtremadura.cuatrola.domain.User;
import com.devtremadura.cuatrola.repository.UserRepository;
import com.devtremadura.cuatrola.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User u) {
        return userRepository.save(u);
    }

}
