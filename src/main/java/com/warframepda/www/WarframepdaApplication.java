package com.warframepda.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WarframepdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarframepdaApplication.class, args);
    }

}
