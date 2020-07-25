package com.ibmMq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ibmMqController {
    private static final Logger log = LoggerFactory.getLogger(ibmMqController.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("send")
    public String sendMsg() {
        try {
            jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World");
            return "Success";
        } catch (JmsException e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    @GetMapping("receive")
    public String receiveMsg() {
        try {
            return Objects.requireNonNull(jmsTemplate.receiveAndConvert("DEV.QUEUE.1")).toString();
        } catch (JmsException e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    @JmsListener(destination = "DEV.QUEUE.1")
    public void receiveMessage(String message) {
        log.info("received message : " +message);
    }
}
