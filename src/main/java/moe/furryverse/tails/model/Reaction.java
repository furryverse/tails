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
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 某个主题贴或者回复贴的反应
 *
 * @param reactionId  反应 ID
 * @param created     创建时间
 * @param createdBy   账户 ID
 * @param emoji       表情
 * @param content     短评
 * @param postId      帖子 ID - 与 Post 的 post_id 相关联
 * @param isPublic    是否公开
 * @param isLocked    是否锁定
 * @param isArchived  是否归档
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("reactions")
public record Reaction(
        @Id @JsonProperty("reaction_id") @NotNull String reactionId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("emoji") @JsonProperty("emoji") @NotNull String emoji,
        @Field("content") @JsonProperty("content") @NotNull String content,

        // 关联键
        @Field("post_id") @JsonProperty("post_id") @NotNull String postId,

        // 管理
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Traceable {
}
