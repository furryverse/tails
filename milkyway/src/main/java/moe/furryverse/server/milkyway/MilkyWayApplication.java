package moe.furryverse.server.milkyway;

import moe.furryverse.server.alnitak.AlnitakApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MilkyWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlnitakApplication.class, args);
    }

    @SuppressWarnings("SpellCheckingInspection unused")
    @ComponentScan(basePackages = "moe.furryverse.server")
    static class Import { }
}
