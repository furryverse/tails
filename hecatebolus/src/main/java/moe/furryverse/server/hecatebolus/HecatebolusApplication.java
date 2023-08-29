package moe.furryverse.server.hecatebolus;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class HecatebolusApplication {
    public static void main(String[] args) {
        SpringApplication.run(HecatebolusApplication.class, args);
    }
}
