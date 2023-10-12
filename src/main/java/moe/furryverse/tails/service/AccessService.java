package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.data.Session;
import moe.furryverse.tails.security.Access;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessService {
    // token - Session
    final RedisTemplate<String, Session> sessions;
    // account id - token
    final RedisTemplate<String, List<String>> indexes;

    public boolean check(String token, List<Access> access) {
        if (token == null) return false;

        // 获取会话中的令牌
        Session session = sessions.opsForValue().get(token);
        if (session == null) return false;

        // 检查令牌是否过期
        if (session.token().expire() < System.currentTimeMillis()) return false;

        // 检查令牌权限是否足够
        return session.token().access().containsAll(access);
    }

    public boolean check(String token, Access access) {
        return check(token, List.of(access));
    }

    public List<Session> getSession(String accountId) {
        List<String> index = indexes.opsForValue().get(accountId);
        if (index == null) return List.of();

        return sessions.opsForValue().multiGet(index);
    }

    public Session revokeSession(String token) {
        Session session = sessions.opsForValue().get(token);
        if (session == null) return null;

        String accountId = session.token().belong();
        List<String> index = indexes.opsForValue().get(accountId);
        if (index == null) return null;

        index.remove(token);
        indexes.opsForValue().set(accountId, index);
        sessions.delete(token);

        return session;
    }
}