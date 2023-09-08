package moe.furryverse.server.nanto;

import moe.furryverse.server.common.annotation.AccessCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({AccessCheck.AccessChecker.class})
@SuppressWarnings("SpellCheckingInspection")
public class NantoApplication {
    public static void main(String[] args) {
        SpringApplication.run(NantoApplication.class, args);
    }
}
