package com.endava.bench.rabbit.workqueue;

import com.endava.bench.rabbit.workqueue.model.MyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitApplication implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        rabbitTemplate.convertAndSend(new MyMessage("testTitle","testBody"));
        System.out.println("Message sent");
    }
}
