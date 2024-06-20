package com.pda.boardapplication;

import com.netflix.discovery.EurekaClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.pda"})
@EnableJpaAuditing
public class BoardApplication {

    @Autowired
    private EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    @PreDestroy
    public void unregister() {
        System.out.println("Shutdown board");
        eurekaClient.shutdown();
    }
}
