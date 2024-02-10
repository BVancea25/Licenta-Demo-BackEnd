package com.example.licentademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class LicentaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicentaDemoApplication.class, args);
    }

}
