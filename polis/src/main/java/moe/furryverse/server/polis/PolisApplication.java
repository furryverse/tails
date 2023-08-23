package moe.furryverse.server.polis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class PolisApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolisApplication.class, args);
    }
}
