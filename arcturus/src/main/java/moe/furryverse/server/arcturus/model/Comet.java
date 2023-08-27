package moe.furryverse.server.arcturus.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 彗星 对应的是具体到某个主题贴或者回复贴的反应
 *
 * @param created   创建时间
 * @param cometId   彗星 ID
 * @param galaxyId  星系 ID
 * @param accountId 账户 ID
 * @param emoji     表情
 * @param content   短评
 */
@Document("comet")
public record Comet(
        @Field("created") long created,
        @Field("comet_id") @NotNull @Id String cometId,
        @Field("galaxy_id") @NotNull String galaxyId,
        @Field("account_id") @NotNull String accountId,
        @Field("emoji") @NotNull String emoji,
        @Field("content") @NotNull String content
) {
}
