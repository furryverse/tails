package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.ServerConfiguration;
import moe.furryverse.tails.data.Session;
import moe.furryverse.tails.utils.Random;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    final ServerConfiguration serverConfiguration;
    // token - Session
    RedisTemplate<String, Session> redisTemplate;
    // account id - token
    RedisTemplate<String, List<String>> listRedisTemplate;

    public String getOAuth() {
        return String.format("%s/login/oauth/authorize?response_type=code&client_id=%s&redirect_uri=%s&state=%s",
                serverConfiguration.getNestsOAuthEndpoint(),
                serverConfiguration.getNestsOAuthClientId(),
                serverConfiguration.getNestsOAuthRedirectUri(),
                Random.uuid()
        );
    }
}