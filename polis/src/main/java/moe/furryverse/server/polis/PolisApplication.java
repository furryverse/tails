package moe.furryverse.server.polis;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class PolisApplication {
    public static void main(String[] args) {
        SpringApplication.run(PolisApplication.class, args);
    }
}
