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

import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Payable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 委托
 *
 * @param commissionId 委托 ID
 * @param created      创建时间
 * @param createdBy    账号
 * @param name         委托名称
 * @param contents     详细内容
 * @param price        价格
 * @param processing   需要进行的交付次数
 * @param isPublic     是否公开
 * @param isLocked     是否锁定
 * @param isArchived   是否归档
 * @param isReviewing  是否在审核中
 * @param isDeleted    是否删除
 */
public record Commission(
        @Id @JsonProperty("commission_id") @NotNull String commissionId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") String name,
        @Field("contents") @JsonProperty("contents") List<String> contents,
        @Field("price") @JsonProperty("price") double price,
        @Field("processing") @JsonProperty("processing") int processing,

        // 管理功能
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable, Attributable, Traceable {
}
