package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 购买电子票后将产生存根 通过扫描存根信息进行检票
 *
 * @param stubId     存根 ID
 * @param created    创建时间
 * @param accountId  购买的账户
 * @param activityId 活动 ID
 * @param ticketId   票据 ID
 * @param orderId    订单 ID
 * @param startTime  票据有效时间
 * @param endTime    票据结束时间
 * @param isUsed     是否已使用
 * @param usedAt     在什么时间使用了
 */
@Document("stubs")
public record Stub(
        @Field("stub_id") @JsonProperty("stub_id") String stubId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("activity_id") @JsonProperty("activity_id") String activityId,
        @Field("ticket_id") @JsonProperty("ticket_id") String ticketId,
        @Field("order_id") @JsonProperty("order_id") String orderId,
        @Field("start_time") @JsonProperty("start_time") long startTime,
        @Field("end_time") @JsonProperty("end_time") long endTime,
        @Field("is_used") @JsonProperty("is_used") boolean isUsed,
        @Field("used_at") @JsonProperty("used_at") long usedAt
) {
}
