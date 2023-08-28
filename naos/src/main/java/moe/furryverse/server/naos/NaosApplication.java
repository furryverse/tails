package moe.furryverse.server.naos;

import moe.furryverse.server.common.config.TailsRpcConfig;
import moe.furryverse.server.common.service.TailsRpcAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@SuppressWarnings("SpellCheckingInspection")
@Import({TailsRpcConfig.class, TailsRpcAccessService.class})
public class NaosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NaosApplication.class, args);
    }
}
