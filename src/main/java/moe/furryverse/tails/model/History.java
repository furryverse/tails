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
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 主题贴或者回复贴的 Diff 信息
 *
 * @param historyId 历史 ID
 * @param created   创建时间
 * @param createdBy 修改人账户 ID
 * @param bindId    绑定的单评或者回复 ID
 * @param diffs     内容 Diff 信息
 */
@Document("histories")
public record History(
        @Id @JsonProperty("history_id") @NotNull String historyId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("bind_id") @JsonProperty("bind_id") @NotNull String bindId,
        @Field("diffs") @JsonProperty("diffs") @NotNull List<String> diffs
) {
}
