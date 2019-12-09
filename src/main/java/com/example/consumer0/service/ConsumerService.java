package com.example.consumer0.service;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaHandler
    @KafkaListener(topics = "student")
    public void readMessage(@Payload String msg,
                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key){
        System.out.println("msg from the kafka : "+msg+" key : "+ key);
    }
}
