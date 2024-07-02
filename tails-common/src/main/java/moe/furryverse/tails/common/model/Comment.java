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
import moe.furryverse.tails.common.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 回复贴
 *
 * @param created     创建时间
 * @param commentId   回复 ID
 * @param createdBy   账户 ID
 * @param contents    内容
 * @param bindId      绑定的相关联数据模型
 *                    - 绑定的帖子的 post_id
 *                    - 绑定的小说的 novel_id
 *                    - 绑定的章节的 chapter_id
 *                    - 绑定活动的 activity_id
 *                    - 绑定商城的 shop_id
 *                    - 绑定商品的 item_id
 * @param isPublic    是否公开
 * @param isLocked    是否锁定
 * @param isArchived  是否归档
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("comments")
public record Comment(
        @Id @JsonProperty("comment_id") @NotNull String commentId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("bind_id") @JsonProperty("bind_id") @NotNull String bindId,

        // 管理功能
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Traceable {
}
