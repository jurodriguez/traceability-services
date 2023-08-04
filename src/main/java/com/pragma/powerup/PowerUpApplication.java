package com.pragma.powerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.pragma.powerup.infrastructure.out.mongodb.repository")
public class PowerUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerUpApplication.class, args);
    }

}
