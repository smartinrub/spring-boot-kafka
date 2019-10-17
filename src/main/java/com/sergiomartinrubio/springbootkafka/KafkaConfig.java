package com.sergiomartinrubio.springbootkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
@EnableKafka
@Configuration
public class KafkaConfig {

    @SendTo("secondTopic")
    @KafkaListener(topics = "firstTopic")
    public String listen(ConsumerRecord<String, String> consumerRecord) {
        log.info("Key: " + consumerRecord.key() + ", Value: " + consumerRecord.value());
        return consumerRecord.value().toUpperCase();
    }

}
