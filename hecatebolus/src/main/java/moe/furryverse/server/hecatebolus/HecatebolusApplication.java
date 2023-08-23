package moe.furryverse.server.hecatebolus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class HecatebolusApplication {
    public static void main(String[] args) {
        SpringApplication.run(HecatebolusApplication.class, args);
    }
}
