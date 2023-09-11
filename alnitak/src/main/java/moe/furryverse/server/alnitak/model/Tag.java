package moe.furryverse.server.alnitak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 标签
 *
 * @param created   创建时间
 * @param tagId     标签 ID
 * @param name      标签名称
 * @param color     标签颜色
 * @param accountId 所属的账户 ID
 */
@Document("tags")
public record Tag(
        @Field("created") @JsonProperty("created") long created,
        @Field("tag_id") @JsonProperty("tag_id") @NotNull @Id String tagId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("color") @JsonProperty("color") @NotNull String color,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId
) {
}
