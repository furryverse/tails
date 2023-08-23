package moe.furryverse.server.mira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MiraApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiraApplication.class, args);
    }
}
