package com.rdvmedical.praticiens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicePraticiensApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePraticiensApplication.class, args);
    }
}

