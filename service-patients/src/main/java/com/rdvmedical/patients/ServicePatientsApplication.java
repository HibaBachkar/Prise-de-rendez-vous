package com.rdvmedical.patients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicePatientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePatientsApplication.class, args);
    }
}

