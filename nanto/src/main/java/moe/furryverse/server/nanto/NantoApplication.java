package moe.furryverse.server.nanto;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
public class NantoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NantoApplication.class, args);
    }
}
