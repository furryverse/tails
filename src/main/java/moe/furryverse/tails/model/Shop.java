package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 店铺
 *
 * @param shopId    店铺 ID
 * @param created   创建时间
 * @param accountId 创建的账户
 * @param name      店铺名称
 * @param cover     店铺封面
 * @param tags      标签
 * @param contents  详细介绍内容
 */
@Document("shops")
public record Shop(
        @Id @JsonProperty("shop_id") @NotNull String shopId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("tags") @JsonProperty("tags") @NotNull List<String> tags,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents
) {
}
