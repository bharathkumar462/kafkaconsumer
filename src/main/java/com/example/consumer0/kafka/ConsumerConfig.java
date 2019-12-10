package com.example.consumer0.kafka;

import com.example.consumer0.common.JsonDeserializer;
import com.example.consumer0.model.Customer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class ConsumerConfig {
    @Autowired
    KafkaProperties kafkaProperties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties(),new StringDeserializer(),new JsonDeserializer(Customer.class));
        factory.setConsumerFactory(consumerFactory);
        return factory;

    }
}
