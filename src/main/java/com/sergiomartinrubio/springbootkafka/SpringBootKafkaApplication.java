package com.sergiomartinrubio.springbootkafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

@Slf4j
@SpringBootApplication
public class SpringBootKafkaApplication implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> template;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        template.send("firstTopic", "0", "Hello World");
        template.send("firstTopic", "1","Goodbye");
        template.send(MessageBuilder.createMessage("Hola!",
                new MessageHeaders(Map.of(KafkaHeaders.TOPIC, "secondTopic", KafkaHeaders.MESSAGE_KEY, "2"))));

        // Async callback
        ProducerRecord<String, String> record = new ProducerRecord<>("firstTopic", "3", "Bonjour");
        ListenableFuture<SendResult<String, String>> future = template.send(record);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Excellent, the " + result.getProducerRecord().value() + " has been sent to the topic " + result.getProducerRecord().topic()
                        + "!");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Sorry, something went wrong!");
            }
        });
        log.info("Done");
    }
}
