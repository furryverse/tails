package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 订单
 *
 * @param orderId   订单 ID
 * @param created   创建时间
 * @param accountId 创建订单的账户 ID
 * @param title     订单标题
 * @param bindId    订单绑定的商品 ID 可以是其他带 price 字段的实体
 * @param sellPrice 商品在商品页面的出售价格
 * @param buyPrice  实际需要付款的价格
 * @param status    订单状态
 */
@Document("orders")
public record Order(
        @Field("order_id") @JsonProperty("order_id") String orderId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("title") @JsonProperty("title") String title,
        @Field("bind_id") @JsonProperty("bind_id") String bindId,
        @Field("sell_price") @JsonProperty("sell_price") int sellPrice,
        @Field("buy_price") @JsonProperty("buy_price") int buyPrice,
        @Field("status") @JsonProperty("status") OrderStatus status
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
