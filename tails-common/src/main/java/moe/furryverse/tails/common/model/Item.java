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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.common.interfaces.Attributable;
import moe.furryverse.tails.common.interfaces.Payable;
import moe.furryverse.tails.common.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

/**
 * 商城物品
 *
 * @param itemId      物品唯一 ID
 * @param created     创建时间
 * @param createdBy   创建的账户
 * @param name        商品名称
 * @param cover       封面
 * @param tags        商品标签
 * @param shows       商品展示图
 * @param contents    商品详细介绍内容
 * @param price       价格
 * @param stock       库存
 * @param shopId      店铺 ID - 与 Shop 的 shop_id 相关联
 * @param isPublic    是否公开
 * @param isLocked    是否锁定
 * @param isArchived  是否归档
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("items")
public record Item(
        @Id @JsonProperty("item_id") @NotNull String itemId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("tags") @JsonProperty("tags") @NotNull Set<String> tags,
        @Field("shows") @JsonProperty("shows") @NotNull List<String> shows,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,
        @Field("price") @JsonProperty("price") double price,
        @Field("stock") @JsonIgnore int stock,

        // 关联键
        @Field("shop_id") @JsonProperty("shop_id") @Nullable String shopId,

        // 管理
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable, Attributable, Traceable {
}
