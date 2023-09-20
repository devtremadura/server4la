package com.devtremadura.cuatrola.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

//@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class RedisConfig {

    @Autowired
    Environment environment;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        String host = Objects.requireNonNull(environment.getProperty("spring.data.redis.host"));
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("spring.data.redis.port")));
        String password = environment.getProperty("spring.data.redis.password");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

}
