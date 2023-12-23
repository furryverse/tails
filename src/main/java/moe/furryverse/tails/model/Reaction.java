package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 某个主题贴或者回复贴的反应
 *
 * @param reactionId 反应 ID
 * @param created    创建时间
 * @param accountId  账户 ID
 * @param emoji      表情
 * @param content    短评
 * @param postId     帖子 ID - 与 Post 的 post_id 相关联
 */
@Document("reactions")
public record Reaction(
        @Id @JsonProperty("reaction_id") @NotNull String reactionId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("emoji") @JsonProperty("emoji") @NotNull String emoji,
        @Field("content") @JsonProperty("content") @NotNull String content,

        // 关联键
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId
) {
}
