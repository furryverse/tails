package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.data.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    // token - Session
    RedisTemplate<String, Session> redisTemplate;
    // account id - token
    RedisTemplate<String, List<String>> listRedisTemplate;
}