package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 回复贴
 *
 * @param created     创建时间
 * @param commentId   回复 ID
 * @param accountId   账户 ID
 * @param contents    内容
 * @param bindId      绑定的相关联数据模型
 *                    - 绑定的帖子的 post_id
 *                    - 绑定的小说的 novel_id
 *                    - 绑定的章节的 chapter_id
 *                    - 绑定活动的 activity_id
 *                    - 绑定商城的 shop_id
 *                    - 绑定商品的 item_id
 * @param isLocked    是否锁定
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("comments")
public record Comment(
        @Id @JsonProperty("comment_id") @NotNull String commentId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("bind_id") @JsonProperty("bind_id") @NotNull String bindId,

        // 管理功能
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) {
}
