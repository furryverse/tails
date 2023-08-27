package moe.furryverse.server.arcturus.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 行星 对应的是主题贴或者回复贴
 *
 * @param created   创建时间
 * @param planetId  行星 ID
 * @param galaxyId  星系 ID
 * @param accountId 账户 ID
 * @param content   内容
 */
@Document("planet")
public record Planet(
        @Field("created") long created,
        @Field("planet_id") String planetId,
        @Field("galaxy_id") @NotNull String galaxyId,
        @Field("account_id") @NotNull String accountId,
        @Field("content") @NotNull String content
) {
}
