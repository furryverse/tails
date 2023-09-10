package moe.furryverse.server.alnitak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 星核 对应的是主题标签
 *
 * @param created   创建时间
 * @param nucleusId 星核 ID
 * @param name      星核名称
 * @param color     星核颜色
 * @param accountId 所属的账户 ID
 */
@Document("nucleus")
public record Nucleus(
        @Field("created") @JsonProperty("created") long created,
        @Field("nucleus_id") @JsonProperty("nucleus_id") @NotNull @Id String nucleusId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("color") @JsonProperty("color") @NotNull String color,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId
) {
}
