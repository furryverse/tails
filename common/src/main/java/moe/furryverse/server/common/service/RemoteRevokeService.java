package moe.furryverse.server.common.service;

/**
 * 弃用远程调用接口
 * 该接口破坏了微服务的无状态特性
 */
@Deprecated
public interface RemoteRevokeService {
    @Deprecated
    void revokeToken(String token);
}
