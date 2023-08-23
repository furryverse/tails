package moe.furryverse.server.alnitak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlnitakApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlnitakApplication.class, args);
    }
}
