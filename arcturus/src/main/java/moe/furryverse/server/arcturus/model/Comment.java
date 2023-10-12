package moe.furryverse.server.arcturus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 回复贴
 *
 * @param created   创建时间
 * @param commentId 回复 ID
 * @param postId    帖子 ID
 * @param accountId 账户 ID
 * @param contents  内容
 */
@Document("comments")
public record Comment(
        @Field("created") @JsonProperty("created") long created,
        @Field("comment_id") @JsonProperty("comment_id") @NotNull String commentId,
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents
) {
}
