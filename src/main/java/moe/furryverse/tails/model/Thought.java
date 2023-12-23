package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 某个主题贴或者回复贴的一行文字的单评
 *
 * @param thoughtId 单评 ID
 * @param created   创建时间
 * @param accountId 账户 ID
 * @param contents  内容
 * @param postId    帖子 ID - 与 Post 的 post_id 相关联
 */
@Document("thoughts")
public record Thought(
        @Field("thought_id") @JsonProperty("thought_id") @NotNull @Id String thoughtId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId
) {
}
