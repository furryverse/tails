/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.common.model;

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
 * @param createdBy 创建订单的账户 ID
 * @param name      订单名
 * @param sellPrice 商品在商品页面的出售价格
 * @param buyPrice  实际需要付款的价格
 * @param status    订单状态
 * @param bindId    订单与多个模型有关系
 *                  - 与 Novel 的 novel_id 相关
 *                  - 与 Chapter 的 chapter_id 相关
 *                  - 与 Ticket 的 ticket_id 相关
 *                  - 与 Item 的 item_id 相关
 *                  - 与 Commission 的 commission_id 相关
 */
@Document("orders")
public record Order(
        @Id @JsonProperty("order_id") @NotNull String orderId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("sell_price") @JsonProperty("sell_price") int sellPrice,
        @Field("buy_price") @JsonProperty("buy_price") int buyPrice,
        @Field("status") @JsonProperty("status") @NotNull OrderStatus status,
        @Field("type") @JsonProperty("type") @NotNull OrderType type,

        // 关联键
        @Field("bind_id") @JsonProperty("bind_id") @NotNull String bindId
) {
    public enum OrderType {
        @JsonProperty("novel") NOVEL,
        @JsonProperty("chapter") CHAPTER,
        @JsonProperty("ticket") TICKET,
        @JsonProperty("item") ITEM,
        @JsonProperty("commission") COMMISSION
    }

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
