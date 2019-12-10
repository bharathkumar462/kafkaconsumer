package com.example.consumer0.service;

import com.example.consumer0.model.Customer;
import com.example.consumer0.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Autowired
    CustomerRepo customerRepo;

    final String CREATE_CUSTOMER="insert";
    final String UPDATE_CUSTOMER="update";
    final String DELETE_CUSTOMER="delete";
    @KafkaHandler
    @KafkaListener(topics = "student")
    public void readMessage(@Payload Customer msg,
                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key){
        if(msg.getEventType().equals(UPDATE_CUSTOMER)) {
            if(customerRepo.findById(msg.getId()).isPresent())
            {
                customerRepo.save(msg);
            }
            System.out.println("msg from the kafka : " + msg + " key : " + key);
        }
        else if(msg.getEventType().equals(CREATE_CUSTOMER))
        {
            customerRepo.save(msg);
            System.out.println("msg from the kafka : " + msg + " key : " + key);

        }
        else if(msg.getEventType().equals(DELETE_CUSTOMER))
        {
            if(customerRepo.findById(msg.getId()).isPresent())
            {
                customerRepo.deleteById(msg.getId());
            }

        }
    }
}
