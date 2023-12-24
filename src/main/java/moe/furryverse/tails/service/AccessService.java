package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.security.Session;
import moe.furryverse.tails.security.Token;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AccessService {
    // token - Session
    final RedisTemplate<String, Session> redisTemplate;
    // account id - token
    final RedisTemplate<String, List<String>> listRedisTemplate;

    public Token create(String device, String ip, String useragent, String belong, Set<String> permissions) {
        Token token = new Token(
                RandomUtils.uuid(),
                belong,
                permissions,
                TimeUtils.getMilliUnixTime() + 3 * 24 * 60 * 60 * 1000,
                TimeUtils.getMilliUnixTime()
        );

        Session session = new Session(
                token,
                device,
                ip,
                useragent
        );

        redisTemplate.opsForValue().set(token.token(), session);
        redisTemplate.expire(token.token(), 3 * 24 * 60 * 60, TimeUnit.of(ChronoUnit.SECONDS));
        listRedisTemplate.opsForValue().append(belong, token.token());

        return token;
    }

    public boolean check(String token, Set<String> requires) {
        if (token == null) return false;

        // 获取会话中的令牌
        Session session = redisTemplate.opsForValue().get(token);
        if (session == null) return false;

        // 检查令牌是否过期
        if (session.token().expire() < System.currentTimeMillis()) return false;

        // 检查令牌权限是否足够
        return session.token().access().containsAll(requires);
    }

    public boolean check(String token, String require) {
        return check(token, Set.of(require));
    }

    public List<Session> getSession(String accountId) {
        List<String> index = listRedisTemplate.opsForValue().get(accountId);
        if (index == null) return List.of();

        return redisTemplate.opsForValue().multiGet(index);
    }

    public Session revokeSession(String token) {
        Session session = redisTemplate.opsForValue().get(token);
        if (session == null) return null;

        String accountId = session.token().belong();
        List<String> index = listRedisTemplate.opsForValue().get(accountId);
        if (index == null) return null;

        index.remove(token);
        listRedisTemplate.opsForValue().set(accountId, index);
        redisTemplate.delete(token);

        return session;
    }
}
