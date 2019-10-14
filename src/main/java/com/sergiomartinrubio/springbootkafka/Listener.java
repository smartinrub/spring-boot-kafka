package com.sergiomartinrubio.springbootkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Listener {

    public final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(id = "containerId", topics = "firstTopic")
    public void listen(String message) {
        log.info("---------->" + message);
        latch.countDown();
    }
}
