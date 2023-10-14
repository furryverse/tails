package moe.furryverse.tails.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ServerConfiguration {
    @Value("${tails.server.nests.oauth.endpoint}")
    String nestsOAuthEndpoint;
    @Value("${tails.server.nests.oauth.client-id}")
    String nestsOAuthClientId;
    @Value("${tails.server.nests.oauth.client-secret}")
    String nestsOAuthClientSecret;
    @Value("${tails.server.nests.oauth.redirect-uri}")
    String nestsOAuthRedirectUri;
}
