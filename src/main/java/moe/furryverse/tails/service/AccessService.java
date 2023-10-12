package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.data.Session;
import moe.furryverse.tails.security.Access;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccessService {
    final RedisTemplate<String, HashMap<String, Session>> sessions;

    public boolean check(String token, String accountId, List<Access> access) {
        if (token == null || accountId == null) return false;

        // 获取会话
        Map<String, Session> set = sessions.opsForValue().get(accountId);
        if (set == null) return false;

        // 获取会话中的令牌
        Session session = set.get(token);
        if (session == null) return false;

        // 检查令牌是否过期
        if (session.token().expire() < System.currentTimeMillis()) return false;

        // 检查令牌权限是否足够
        return session.token().access().containsAll(access);
    }

    public boolean check(String token, String accountId, Access access) {
        return check(token, accountId, List.of(access));
    }

    public List<Session> getSession(String accountId) {
        // 获取会话
        Map<String, Session> set = sessions.opsForValue().get(accountId);

        // 只需要列表
        return set == null ? null : List.copyOf(set.values());
    }

    public Session revokeSession(String token, String accountId) {
        HashMap<String, Session> set = sessions.opsForValue().get(accountId);
        if (set == null) return null;

        Session session = set.remove(token);

        if (session == null) {
            return null;
        } else {
            // 需要回写 Redis 更新会话信息
            sessions.opsForValue().set(accountId, set);
            return session;
        }
    }
}