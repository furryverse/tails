package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 商城物品
 *
 * @param itemId    物品唯一 ID
 * @param created   创建时间
 * @param accountId 创建的账户
 * @param name      商品名称
 * @param cover     封面
 * @param tags      商品标签
 * @param shows     商品展示图
 * @param contents  商品详细介绍内容
 * @param price     价格
 * @param stock     库存
 * @param shopId    店铺 ID - 与 Shop 的 shop_id 相关联
 */
@Document("items")
public record Item(
        @Field("item_id") @JsonProperty("item_id") @NotNull @Id String itemId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("tags") @JsonProperty("tags") @NotNull List<String> tags,
        @Field("shows") @JsonProperty("shows") @NotNull List<String> shows,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,
        @Field("price") @JsonProperty("price") double price,
        @Field("stock") @JsonIgnore int stock,

        // 关联键
        @Field("shop_id") @JsonProperty("shop_id") @Nullable String shopId
) {
}
