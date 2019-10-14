package com.sergiomartinrubio.springbootkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@SpringBootApplication
public class SpringBootKafkaApplication implements CommandLineRunner {

    @Autowired
    KafkaTemplate<String, String> template;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        template.send("firstTopic", "0", "Hello World");
        template.send("firstTopic", "1","Goodbye");
        log.info("Done");
    }
}
