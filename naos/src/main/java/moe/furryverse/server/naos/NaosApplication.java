package moe.furryverse.server.naos;

import moe.furryverse.server.common.annotation.AccessCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({AccessCheck.AccessChecker.class})
@SuppressWarnings("SpellCheckingInspection")
public class NaosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaosApplication.class, args);
    }
}
