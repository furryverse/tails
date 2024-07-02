/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.server.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.security.Session;
import moe.furryverse.tails.common.security.Token;
import moe.furryverse.tails.common.utils.RandomUtils;
import moe.furryverse.tails.common.utils.TimeUtils;
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
        return session.token().permissions().containsAll(requires);
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

    public String getAccountId(String token) {
        Session session = redisTemplate.opsForValue().get(token);
        if (session == null) return null;

        return session.token().belong();
    }

    /**
     * 接口用于接受申请临时密钥 比如登陆前发验证邮件
     * 此外还有限流的作用
     *
     * @param requires  请求的权限
     * @param expires   过期时间
     * @param email     邮件
     * @param ip        IP 地址
     * @param userAgent 用户代理
     * @return 返回临时密钥
     */
    public Token getTempToken(Set<String> requires, long expires, String email, String ip, String userAgent) {
        return null;
    }
}
