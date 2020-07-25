package com.ibmMq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ibmMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ibmMqApplication.class);
    }
}
