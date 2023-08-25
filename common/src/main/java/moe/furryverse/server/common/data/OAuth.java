package moe.furryverse.server.common.data;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("oauth")
@AllArgsConstructor
public class OAuth {
    // 唯一 ID
    @NotNull @Id String id;
    // OAuth 提供商的 OpenID
    @NotNull String openid;
    // 创建时间
    long created;
    // 用户唯一 ID
    @NotNull String uid;
    // 授权平台
    @NotNull String provider;
}