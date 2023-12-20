package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 */
@Document("items")
public record Item(
        @Field("item_id") @JsonProperty("item_id") String itemId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("name") @JsonProperty("name") String name,
        @Field("cover") @JsonProperty("cover") String cover,
        @Field("tags") @JsonProperty("tags") List<String> tags,
        @Field("shows") @JsonProperty("shows") List<String> shows,
        @Field("contents") @JsonProperty("contents") List<String> contents,
        @Field("price") @JsonProperty("price") int price,
        @Field("stock") @JsonProperty("stock") int stock
) {
}
