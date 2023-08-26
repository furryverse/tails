package moe.furryverse.server.alnitak.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 星球
 *
 * @param planetId     唯一 ID
 * @param accountId    所属账户 ID
 * @param created      创建时间
 * @param title        标题
 * @param titleHistory 标题历史
 * @param isPublic     是否公开
 * @param viewer       访问者 具有访问权限
 * @param collaborator 协作者 具有编辑权限
 */
@Document("planet")
public record Planet(
        @Field("planet_id") @NotNull @Id String planetId,
        @Field("account_id") @NotNull String accountId,
        @Field("created") long created,
        @Field("content") @NotNull String title,
        @Field("title_history") @NotNull List<String> titleHistory,
        @Field("is_public") boolean isPublic,
        @Field("viewer") @NotNull List<String> viewer,
        @Field("collaborator") @NotNull List<String> collaborator
) {
}
