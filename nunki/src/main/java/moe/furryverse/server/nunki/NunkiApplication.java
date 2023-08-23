package moe.furryverse.server.nunki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NunkiApplication {
    public static void main(String[] args) {
        SpringApplication.run(NunkiApplication.class, args);
    }
}
