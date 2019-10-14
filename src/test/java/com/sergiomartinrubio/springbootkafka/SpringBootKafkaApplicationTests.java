package com.sergiomartinrubio.springbootkafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootKafkaApplicationTests {

    @Autowired
    Listener listener;


    @Autowired
    KafkaTemplate<Integer, String> template;

    @Test
    public void firstKafkaTest() throws InterruptedException {
        template.send("firstTopic", 1, "Hello World");
        template.flush();
        assertTrue(listener.latch.await(10, TimeUnit.SECONDS));
    }

}
