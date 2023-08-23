package moe.furryverse.server.borealis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class BorealisApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorealisApplication.class, args);
    }
}
