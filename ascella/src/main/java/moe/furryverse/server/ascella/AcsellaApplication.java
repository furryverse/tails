package moe.furryverse.server.ascella;

import moe.furryverse.server.common.annotation.AccessCheck;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@EnableDubbo
@EnableDiscoveryClient
@SpringBootApplication
@Import({AccessCheck.AccessChecker.class})
@SuppressWarnings("SpellCheckingInspection")
public class AcsellaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcsellaApplication.class, args);
    }
}
