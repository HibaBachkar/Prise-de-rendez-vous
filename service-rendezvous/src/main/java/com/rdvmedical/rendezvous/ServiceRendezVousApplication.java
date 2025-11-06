package com.rdvmedical.rendezvous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceRendezVousApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRendezVousApplication.class, args);
    }
}

