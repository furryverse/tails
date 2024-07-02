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

/**
 * OAuth 授权数据
 *
 * @param oauthId   唯一 ID
 * @param createdBy 用户唯一 ID
 * @param created   创建时间
 * @param openid    OAuth 提供商的 OpenID
 * @param provider  授权平台
 */
@SuppressWarnings("SpellCheckingInspection")
@Document("oauths")
public record OAuth(
        @Id @JsonProperty("oauth_id") @NotNull String oauthId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("openid") @JsonProperty("openid") @NotNull String openid,
        @Field("provider") @JsonProperty("provider") @NotNull String provider
) {
}