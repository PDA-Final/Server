package com.pda.userapplication;

import com.netflix.discovery.EurekaClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.pda"})
@EnableJpaAuditing
public class UserApplication {

    @Autowired
    private EurekaClient eurekaClient;

    @PreDestroy
    public void unregister() {
        System.out.println("Shutdown user");
        eurekaClient.shutdown();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
