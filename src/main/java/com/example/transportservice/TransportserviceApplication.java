package com.example.transportservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class TransportserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportserviceApplication.class, args);
    }
}
