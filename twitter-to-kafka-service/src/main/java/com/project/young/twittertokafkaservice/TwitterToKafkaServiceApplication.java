package com.project.young.twittertokafkaservice;

import com.project.young.config.TwitterToKafkaServiceConfigData;
import com.project.young.twittertokafkaservice.init.StreamInitializer;
import com.project.young.twittertokafkaservice.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@RequiredArgsConstructor
@SpringBootApplication
@ComponentScan(basePackages = "com.project.young")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App starts...");
        streamInitializer.init();
        streamRunner.start();
    }
}
