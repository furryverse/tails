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
import moe.furryverse.tails.interfaces.Manageable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * 板块 / 分区
 *
 * @param categoryId       分类 ID
 * @param created          创建时间
 * @param createdBy        所属账号
 * @param name             分类名称
 * @param icon             分类图标
 * @param color            主题颜色
 * @param banner           横幅
 * @param bannerBackground 横幅背景
 * @param background       背景
 * @param description      描述
 * @param reviewers        审核员（可以对帖子进行审核 并决定是否通过）
 * @param administrators   管理员 （可以对帖子进行删除、锁定、置顶、编辑等操作）
 * @param editors          编辑员 （可以对帖子进行编辑）
 * @param viewers          查看员 （在不公开状态下可以查看帖子）
 * @param isPublic         是否公开 （公开的板块可以被所有人查看）
 * @param isLocked         是否锁定 （锁定的板块不允许发帖）
 *                         (锁定的板块可以被所有人查看，但不能在该板块发帖)
 * @param isArchived       是否归档
 * @param isReviewing      是否在审核中
 * @param isDeleted        是否删除
 *                         (删除的板块不允许发帖，但可以在该板块发帖)
 */
@Document("categories")
public record Category(
        // 基本信息
        @Id @JsonProperty("category_id") @NotNull String categoryId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("icon") @JsonProperty("icon") @Nullable String icon,
        @Field("color") @JsonProperty("color") @Nullable String color,
        @Field("banner") @JsonProperty("banner") @Nullable String banner,
        @Field("banner_background") @JsonProperty("banner_background") @Nullable String bannerBackground,
        @Field("background") @JsonProperty("background") @Nullable String background,
        @Field("description") @JsonProperty("description") @Nullable String description,

        // 管理功能
        @Field("reviewers") @JsonProperty("reviewers") @NotNull Set<String> reviewers,
        @Field("administrators") @JsonProperty("administrators") @NotNull Set<String> administrators,
        @Field("editors") @JsonProperty("editors") @NotNull Set<String> editors,
        @Field("viewers") @JsonProperty("viewers") @NotNull Set<String> viewers,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Manageable, Traceable {
}
