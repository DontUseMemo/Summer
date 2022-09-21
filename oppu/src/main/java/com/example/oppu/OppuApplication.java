package com.example.oppu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OppuApplication {

    public static void main(String[] args) {
        SpringApplication.run(OppuApplication.class, args);
    }

}
