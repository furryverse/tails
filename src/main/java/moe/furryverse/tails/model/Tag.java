package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 标签
 *
 * @param tagId       标签 ID
 * @param created     创建时间
 * @param createdBy   所属的账户 ID
 * @param name        标签名称
 * @param color       标签颜色
 * @param isPublic    是否公开
 * @param isLocked    是否锁定
 * @param isArchived  是否归档
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("tags")
public record Tag(
        @Id @JsonProperty("tag_id") @NotNull String tagId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("color") @JsonProperty("color") @NotNull String color,

        // 管理功能
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Traceable {
}
