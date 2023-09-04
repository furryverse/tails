package moe.furryverse.server.borealis;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class BorealisApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorealisApplication.class, args);
    }
}
