package moe.furryverse.server.nanto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class NantoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NantoApplication.class, args);
    }
}
