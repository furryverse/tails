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

package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Payable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 票据 一场活动里会有很多种类型的票据
 *
 * @param ticketId    票据 ID
 * @param created     创建时间
 * @param createdBy   创建该票据的账户 ID
 * @param name        票据名称
 * @param cover       票据封面
 * @param stubCover   票据存根的封面
 * @param description 描述
 * @param price       价格
 * @param stock       数量
 * @param contents    详细介绍内容
 * @param activityId  活动 ID - 与 Activity 的 activity_id 相关联
 * @param isPublic    是否公开
 * @param isLocked    是否锁定
 * @param isArchived  是否归档
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("tickets")
public record Ticket(
        @Id @JsonProperty("ticket_id") @NotNull String ticketId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") String cover,
        @Field("stub_cover") @JsonProperty("stub_cover") String stubCover,
        @Field("description") @JsonProperty("description") @NotNull String description,
        @Field("price") @JsonProperty("price") double price,
        @Field("stock") @JsonIgnore int stock,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("activity_id") @JsonProperty("activity_id") String activityId,

        // 管理功能
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable, Attributable, Traceable {
}
