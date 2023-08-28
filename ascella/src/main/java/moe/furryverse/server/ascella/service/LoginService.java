package moe.furryverse.server.ascella.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.ascella.data.Session;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    final RedisTemplate<String, Session> sessions;
}
