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
import moe.furryverse.tails.common.interfaces.Attributable;
import moe.furryverse.tails.common.interfaces.Manageable;
import moe.furryverse.tails.common.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

/**
 * 店铺
 *
 * @param shopId         店铺 ID
 * @param created        创建时间
 * @param createdBy      创建的账户
 * @param name           店铺名称
 * @param cover          店铺封面
 * @param tags           标签
 * @param contents       详细介绍内容
 * @param administrators 管理员
 * @param isPublic       是否公开
 * @param isLocked       是否锁定
 * @param isArchived     是否归档
 * @param isReviewing    是否在审核中
 * @param isDeleted      是否删除
 */
@Document("shops")
public record Shop(
        @Id @JsonProperty("shop_id") @NotNull String shopId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("tags") @JsonProperty("tags") @NotNull Set<String> tags,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 管理功能
        @Field("administrators") @JsonProperty("administrators") @Nullable Set<String> administrators,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Manageable, Traceable {
}
