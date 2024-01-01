package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 某个主题贴或者回复贴的反应
 *
 * @param reactionId  反应 ID
 * @param created     创建时间
 * @param createdBy   账户 ID
 * @param emoji       表情
 * @param content     短评
 * @param postId      帖子 ID - 与 Post 的 post_id 相关联
 * @param isLocked    是否锁定
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("reactions")
public record Reaction(
        @Id @JsonProperty("reaction_id") @NotNull String reactionId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("emoji") @JsonProperty("emoji") @NotNull String emoji,
        @Field("content") @JsonProperty("content") @NotNull String content,

        // 关联键
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,

        // 管理
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) {
}
