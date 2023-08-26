package moe.furryverse.server.alnitak.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 用户的星球
 *
 * @param planetId  唯一 ID
 * @param created   创建时间
 * @param accountId 所属的账号 ID
 * @param content   内容
 * @param isPublic  是否公开
 * @param access    在非公开状态具有访问权限的
 * @param isDraft   是否为草稿
 */
@Document("planet")
public record Planet(
        @Field("planet_id") @NotNull @Id String planetId,
        @Field("account_id") @NotNull String accountId,
        @Field("created") long created,
        @Field("content") @NotNull String content,
        @Field("is_public") boolean isPublic,
        @Field("access") List<String> access,
        @Field("is_draft") boolean isDraft
) {
}
