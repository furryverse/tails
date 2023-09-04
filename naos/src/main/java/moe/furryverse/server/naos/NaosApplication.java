package moe.furryverse.server.naos;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class NaosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaosApplication.class, args);
    }
}
