package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 订单
 *
 * @param orderId   订单 ID
 * @param created   创建时间
 * @param accountId 创建订单的账户 ID
 * @param title     订单标题
 * @param sellPrice 商品在商品页面的出售价格
 * @param buyPrice  实际需要付款的价格
 * @param status    订单状态
 * @param bindId    订单与多个模型有关系
 *                  - 与 Novel 的 novel_id 相关
 *                  - 与 Chapter 的 chapter_id 相关
 *                  - 与 Ticket 的 ticket_id 相关
 *                  - 与 Item 的 item_id 相关
 */
@Document("orders")
public record Order(
        @Id @JsonProperty("order_id") @NotNull String orderId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("title") @JsonProperty("title") @NotNull String title,
        @Field("sell_price") @JsonProperty("sell_price") int sellPrice,
        @Field("buy_price") @JsonProperty("buy_price") int buyPrice,
        @Field("status") @JsonProperty("status") @NotNull OrderStatus status,

        // 关联键
        @Field("bind_id") @JsonProperty("bind_id") @NotNull String bindId
) {
    public enum OrderStatus {
        // 订单创建
        @JsonProperty("created") CREATED,
        // 等待支付
        @JsonProperty("need_pay") NEED_PAY,
        // 支付完成
        @JsonProperty("paid") PAID,
        // 订单完成
        @JsonProperty("success") SUCCESS,
        // 取消订单
        @JsonProperty("cancelled") CANCELLED
    }
}
