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


    @KafkaHandler
    @KafkaListener(topics = "student")
    public void readMessage(@Payload String msg,
                            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key){
        String[] data = msg.split(",");
        if(data.length==3) {
            Customer customer = new Customer();
            customer.setId(Long.parseLong(data[0]));
            customer.setName(data[1]);
            customer.setPhoneNumber(data[2]);
            customerRepo.save(customer);
            System.out.println("msg from the kafka : " + msg + " key : " + key);
        }
        else if(data.length==2)
        {
            Customer customer = new Customer();
            customer.setId(Long.parseLong(key));
            customer.setName(data[0]);
            customer.setPhoneNumber(data[1]);
            customerRepo.save(customer);
            System.out.println("msg from the kafka : " + msg + " key : " + key);

        }
        else if(data.length==1)
        {
            customerRepo.deleteById(Long.parseLong(msg));
        }
    }
}
