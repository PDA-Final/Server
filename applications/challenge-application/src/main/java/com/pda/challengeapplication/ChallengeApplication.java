package com.pda.challengeapplication;

import com.netflix.discovery.EurekaClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication (scanBasePackages = {"com.pda"})
public class ChallengeApplication {

    @Autowired
    private EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

    @PreDestroy
    public void unregister() {
        System.out.println("Shutdown board");
        eurekaClient.shutdown();
    }
}
