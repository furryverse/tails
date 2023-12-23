package moe.furryverse.tails.service;

import moe.furryverse.tails.security.Token;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LimitingService {
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
