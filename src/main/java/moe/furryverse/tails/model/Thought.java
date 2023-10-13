package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 某个主题贴或者回复贴的一行文字的单评
 *
 * @param created   创建时间
 * @param thoughtId 单评 ID
 * @param postId    帖子 ID
 * @param accountId 账户 ID
 * @param contents  内容
 */
@Document("thoughts")
public record Thought(
        @Field("created") @JsonProperty("created") long created,
        @Field("thought_id") @JsonProperty("thought_id") @NotNull String thoughtId,
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents
) {
}
