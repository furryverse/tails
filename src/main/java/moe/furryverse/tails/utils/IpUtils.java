package moe.furryverse.tails.utils;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

public class IpUtils {
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");

        // 考虑 nginx 转发情况下
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("Proxy-Client-IP");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("WL-Proxy-Client-IP");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) ip = request.getRemoteAddr();

        // 代理状态下可能存在多个 ip 地址
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
