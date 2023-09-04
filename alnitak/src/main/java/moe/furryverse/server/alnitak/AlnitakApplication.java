package moe.furryverse.server.alnitak;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class AlnitakApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlnitakApplication.class, args);
    }
}
