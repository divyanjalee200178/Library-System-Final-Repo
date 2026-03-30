package org.example.lendingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.lendingserver.client")
public class LendingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LendingServerApplication.class, args);
    }

}
