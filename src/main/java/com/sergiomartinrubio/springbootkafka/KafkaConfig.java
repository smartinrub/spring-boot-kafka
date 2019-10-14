package com.sergiomartinrubio.springbootkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
public class KafkaConfig {

    @KafkaListener(topics = "firstTopic")
    public void listen(ConsumerRecord<String, String> consumerRecord) {
        log.info("Key: " + consumerRecord.key() + ", Value: " + consumerRecord.value());
    }
}
