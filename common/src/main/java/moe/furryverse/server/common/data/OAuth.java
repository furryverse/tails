package moe.furryverse.server.common.data;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("oauth")
@AllArgsConstructor
public class OAuth {
    // OAuth 提供商的 OpenID
    String openid;
    // 创建时间
    long created;
    // 用户唯一 ID
    String uid;
    // 授权平台
    String provider;
}