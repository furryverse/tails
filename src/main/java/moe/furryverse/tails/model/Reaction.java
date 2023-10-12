package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 某个主题贴或者回复贴的反应
 *
 * @param created    创建时间
 * @param reactionId 反应 ID
 * @param postId     帖子 ID
 * @param accountId  账户 ID
 * @param emoji      表情
 * @param content    短评
 */
@Document("reactions")
public record Reaction(
        @Field("created") @JsonProperty("created") long created,
        @Field("reaction_id") @JsonProperty("reaction_id") @NotNull @Id String reactionId,
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("emoji") @JsonProperty("emoji") @NotNull String emoji,
        @Field("content") @JsonProperty("content") @NotNull String content
) {
}
