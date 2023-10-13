package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
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
@SuppressWarnings("SpellCheckingInspection")
@Document("oauths")
public record OAuth(
        @Field("oauth_id") @JsonProperty("oauth_id") @NotNull String oauthId,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("created") @JsonProperty("created") long created,
        @Field("openid") @JsonProperty("openid") @NotNull String openid,
        @Field("provider") @JsonProperty("provider") @NotNull String provider
) {
}