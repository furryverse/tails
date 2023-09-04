package moe.furryverse.server.ascella;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class AcsellaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcsellaApplication.class, args);
    }
}
