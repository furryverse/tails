package moe.furryverse.server.ascella.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.ascella.data.Session;
import moe.furryverse.server.common.security.Access;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessService {
    final RedisTemplate<String, Session> sessions;

    public boolean hasAccess(String accountId, List<Access> access) {
        if (accountId == null) return false;

        Session session = sessions.opsForValue().get(accountId);
        if (session == null) return false;

        // 检查 token 是否过期
        if (session.token().expire() < System.currentTimeMillis()) {
            sessions.delete(accountId);
            return false;
        }

        // 是否包含全部的权限
        if (access == null || access.isEmpty()) return true;
        return session.token().access().containsAll(access);
    }

    public boolean hasAccess(String accountId, Access access) {
        return hasAccess(accountId, List.of(access));
    }
}
