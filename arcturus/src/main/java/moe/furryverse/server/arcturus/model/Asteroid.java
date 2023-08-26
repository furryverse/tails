package moe.furryverse.server.arcturus.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Asteroid 评论
 *
 * @param asteroidId 唯一 ID
 * @param planetId   所属星球 ID
 * @param accountId  所属账户 ID
 * @param created    创建时间
 * @param content    内容
 */
@Document("asteroid")
public record Asteroid(
        @Field("asteroid_id") @NotNull @Id String asteroidId,
        @Field("planet_id") @NotNull String planetId,
        @Field("account_id") @NotNull String accountId,
        @Field("created") long created,
        @Field("content") @NotNull String content
) {
}
