package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 某个主题贴或者回复贴的一行文字的单评
 *
 * @param thoughtId   单评 ID
 * @param created     创建时间
 * @param createdBy   账户 ID
 * @param contents    内容
 * @param postId      帖子 ID - 与 Post 的 post_id 相关联
 * @param isLocked    是否锁定
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("thoughts")
public record Thought(
        @Id @JsonProperty("thought_id") @NotNull String thoughtId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,

        // 管理
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Traceable {
}
