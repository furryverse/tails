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
import moe.furryverse.tails.common.interfaces.Contributable;
import moe.furryverse.tails.common.interfaces.Traceable;
import moe.furryverse.tails.common.interfaces.Viewable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * 帖子 / 主题
 *
 * @param postId        帖子 ID
 * @param created       创建时间
 * @param createdBy     创建的账户
 * @param title         主题标题
 * @param background    背景
 * @param categoryId    分类 ID - 本字段与 Category 中的 category_id 相关联
 * @param tags          标签 - 本字段的值与 Tag 中的 tag_id 相关联
 * @param viewers       未公开状态下可以查看帖子的用户
 * @param collaborators 可以编辑帖子的用户
 * @param isPublic      是否公开
 * @param isLocked      是否锁定（不允许查看不允许修改）
 * @param isReviewing   是否正在审核
 * @param isArchived    是否已归档（不允许修改）
 * @param isDeleted     是否删除
 */
@Document("posts")
public record Post(
        // 基本信息
        @Id @JsonProperty("post_id") @NotNull String postId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("title") @JsonProperty("title") @NotNull String title,
        @Field("background") @JsonProperty("background") @Nullable String background,

        // 关联键
        @Field("category_id") @JsonProperty("category_id") @NotNull String categoryId,
        @Field("tags") @JsonProperty("tags") @NotNull Set<String> tags,

        // 管理功能
        @Field("viewers") @JsonProperty("viewers") @NotNull Set<String> viewers,
        @Field("collaborators") @JsonProperty("collaborators") @NotNull Set<String> collaborators,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Contributable, Traceable, Viewable {
}
