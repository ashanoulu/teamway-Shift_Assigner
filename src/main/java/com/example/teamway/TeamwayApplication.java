package com.example.teamway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.teamway", "com.example.teamway.validation"})
public class TeamwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamwayApplication.class, args);
    }

}
