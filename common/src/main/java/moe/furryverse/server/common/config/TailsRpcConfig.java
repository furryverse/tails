package moe.furryverse.server.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tails.rpc")
public class TailsRpcConfig {
    // 自定义配置 tails.rpc.machine-id
    String id;

    // 自定义配置 tails.rpc.machine-secret
    String secret;

    // 自定义配置 tails.rpc.url
    String url;
}
