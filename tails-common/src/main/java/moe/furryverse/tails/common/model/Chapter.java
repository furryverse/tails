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
import moe.furryverse.tails.common.interfaces.Payable;
import moe.furryverse.tails.common.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 小说的章节
 *
 * @param chapterId   章节 ID
 * @param created     创建时间
 * @param createdBy   创建人
 * @param name        名字
 * @param contents    内容
 * @param price       单篇收费价格
 * @param novelId     小说 ID - 与 Novel 的 novel_id 相关联
 * @param isDraft     是否为草稿（不公开 编辑不保存历史记录）
 * @param isPublic    是否公开
 * @param isLocked    是否锁定（不允许查看不允许修改）
 * @param isArchived  是否归档
 * @param isReviewing 是否正在审核
 * @param isDeleted   是否删除
 */
@Document("chapters")
public record Chapter(
        @Id @JsonProperty("chapter_id") @NotNull String chapterId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") String name,
        @Field("contents") @JsonProperty("contents") List<String> contents,
        @Field("price") @JsonProperty("price") double price,

        // 关联键
        @Field("novel_id") @JsonProperty("novel_id") String novelId,

        // 管理功能
        @Field("is_draft") @JsonProperty("is_draft") boolean isDraft,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable, Attributable, Traceable {
}
