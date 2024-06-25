package com.pda.alertapplication;

import com.netflix.discovery.EurekaClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.pda"})
@EnableScheduling
@EnableCaching
public class AlertApplication {

    @Autowired
    private EurekaClient eurekaClient;

    @PreDestroy
    public void unregister() {
        eurekaClient.shutdown();
    }

    public static void main(String[] args) {
        SpringApplication.run(AlertApplication.class, args);
    }

}
