package com.example.consumer0.kafka;
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
    public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(kafkaProperties.buildConsumerProperties());
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
