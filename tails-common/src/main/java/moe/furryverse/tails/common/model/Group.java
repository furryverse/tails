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
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/**
 * 一个群组
 *
 * @param groupId      群聊唯一 ID
 * @param created      创建时间
 * @param name         群聊名称
 * @param accountId    创建人
 * @param descriptions 群聊介绍
 * @param members      群聊成员
 * @param admins       群聊管理
 * @param nicks        昵称列表 - Map<Account ID - 昵称>
 * @param awards       称号列表 - Map<Account ID - 称号>
 */
@Document("groups")
public record Group(
        @Id @JsonProperty("group_id") @NotNull String groupId,
        @Field("created") @JsonProperty("created") long created,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("description") @JsonProperty("description") @NotNull List<String> descriptions,
        @Field("members") @JsonProperty("members") @NotNull List<String> members,
        @Field("admins") @JsonProperty("admins") @NotNull List<String> admins,
        @Field("nicks") @JsonProperty("nicks") @NotNull Map<String, String> nicks,
        @Field("awards") @JsonProperty("awards") @NotNull Map<String, String> awards
) {
}
