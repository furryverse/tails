package moe.furryverse.server.common.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * OAuth 授权数据
 *
 * @param oauthId   唯一 ID
 * @param openid    OAuth 提供商的 OpenID
 * @param created   创建时间
 * @param accountId 用户唯一 ID
 * @param provider  授权平台
 */
@Document("oauth")
public record OAuth(
        @Field("oauth_id") @NotNull @Id String oauthId,
        @Field("account_id") @NotNull String accountId,
        @Field("created") long created,
        @Field("openid") @NotNull String openid,
        @Field("provider") @NotNull String provider
) {
}