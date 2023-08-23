package moe.furryverse.server.arcturus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ArcturusApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArcturusApplication.class, args);
    }
}
