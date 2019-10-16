package com.sergiomartinrubio.springbootkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyProducerListener implements ProducerListener<String, String> {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info("Excellent, the " + producerRecord.value() + " has been sent to the topic " + producerRecord.topic() +"!");
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        log.error("Sorry, something went wrong!");
    }
}
