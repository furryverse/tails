package moe.furryverse.tails.data;

import moe.furryverse.tails.security.Token;
import org.jetbrains.annotations.NotNull;

/**
 * Session 用户会话信息
 *
 * @param token     凭据 包含权限信息
 * @param device    设备名
 * @param ip        IP 地址
 * @param useragent 用户识标头
 */
public record Session(
        @NotNull Token token,
        @NotNull String device,
        @NotNull String ip,
        @NotNull String useragent
) {
}
