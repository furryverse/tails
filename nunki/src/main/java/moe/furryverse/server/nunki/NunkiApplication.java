package moe.furryverse.server.nunki;

import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.config.TailsRpcConfig;
import moe.furryverse.server.common.service.TailsRpcAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({TailsRpcConfig.class, TailsRpcAccessService.class, AccessCheck.AccessChecker.class})
public class NunkiApplication {
    public static void main(String[] args) {
        SpringApplication.run(NunkiApplication.class, args);
    }
}
