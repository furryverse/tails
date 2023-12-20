package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 票据 一场活动里会有很多种类型的票据
 *
 * @param ticketId    票据 ID
 * @param created     创建时间
 * @param activityId  活动 ID
 * @param accountId   创建该票据的账户 ID
 * @param name        票据名称
 * @param cover       票据封面
 * @param stubCover   票据存根的封面
 * @param description 描述
 * @param price       价格
 * @param stock       数量
 * @param contents    详细介绍内容
 */
@Document("tickets")
public record Ticket(
        @Field("ticket_id") @JsonProperty("ticket_id") String ticketId,
        @Field("created") @JsonProperty("created") long created,
        @Field("activity_id") @JsonProperty("activity_id") String activityId,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") String cover,
        @Field("stub_cover") @JsonProperty("stub_cover") String stubCover,
        @Field("description") @JsonProperty("description") @NotNull String description,
        @Field("price") @JsonProperty("price") int price,
        @Field("stock") @JsonProperty("stock") int stock,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents
) {
}
