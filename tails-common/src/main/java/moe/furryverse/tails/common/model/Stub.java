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
 * 购买电子票后将产生存根 通过扫描存根信息进行检票
 *
 * @param stubId     存根 ID
 * @param created    创建时间
 * @param createdBy  购买的账户
 * @param startTime  票据有效时间
 * @param endTime    票据结束时间
 * @param isUsed     是否已使用
 * @param usedAt     在什么时间使用了
 * @param activityId 活动 ID - 与 Activity 的 activity_id 相关联
 * @param ticketId   票据 ID - 与 Ticket 的 ticket_id 相关联
 * @param orderId    订单 ID - 与 Order 的 order_id 相关联
 */
@Document("stubs")
public record Stub(
        @Id @JsonProperty("stub_id") @NotNull String stubId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("start_time") @JsonProperty("start_time") long startTime,
        @Field("end_time") @JsonProperty("end_time") long endTime,
        @Field("is_used") @JsonProperty("is_used") boolean isUsed,
        @Field("used_at") @JsonProperty("used_at") long usedAt,

        // 关联键
        @Field("activity_id") @JsonProperty("activity_id") @NotNull String activityId,
        @Field("ticket_id") @JsonProperty("ticket_id") @NotNull String ticketId,
        @Field("order_id") @JsonProperty("order_id") @NotNull String orderId
) {
}
