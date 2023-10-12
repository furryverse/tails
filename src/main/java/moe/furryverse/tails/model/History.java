package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 主题贴或者回复贴的 Diff 信息
 *
 * @param created   创建时间
 * @param historyId 历史 ID
 * @param bindId    绑定的单评或者回复 ID
 * @param diffs     内容 Diff 信息
 * @param accountId 修改人账户 ID
 */
@Document("histories")
public record History(
        @Field("created") @JsonProperty("created") long created,
        @Field("history_id") @JsonProperty("history_id") String historyId,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,
        @Field("bind_id") @JsonProperty("bind_id") String bindId,
        @Field("diffs") @JsonProperty("diffs") List<String> diffs
) {
}
