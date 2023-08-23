package moe.furryverse.server.naos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class NaosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaosApplication.class, args);
    }
}
