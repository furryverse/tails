package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Contributable;
import moe.furryverse.tails.interfaces.Payable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

/**
 * 一本小说
 *
 * @param novelId       小说唯一 ID
 * @param created       创建时间
 * @param createdBy     创建小说的账户 ID
 * @param name          名字
 * @param description   简介
 * @param cover         封面
 * @param price         价格
 * @param tags          标签
 * @param viewers       未公开状态下可以查看帖子的用户
 * @param collaborators 可以编辑帖子的用户
 * @param isPublic      是否公开
 * @param isLocked      是否锁定（不允许查看不允许修改）
 * @param isReviewing   是否正在审核
 * @param isDeleted     是否删除
 */
@Document("novels")
public record Novel(
        @Id @JsonProperty("novel_id") @NotNull String novelId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("description") @JsonProperty("description") @Nullable String description,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("price") @JsonProperty("price") double price,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("tags") @JsonProperty("tags") @NotNull List<String> tags,

        // 管理功能
        @Field("viewers") @JsonProperty("viewers") @NotNull Set<String> viewers,
        @Field("collaborators") @JsonProperty("collaborators") @NotNull Set<String> collaborators,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable, Attributable, Contributable, Traceable {
}
