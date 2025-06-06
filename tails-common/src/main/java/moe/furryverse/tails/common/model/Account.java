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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

/**
 * 用户账户
 *
 * @param accountId   用户唯一 ID
 * @param created     创建时间
 * @param username    用户唯一用户名
 * @param nickname    昵称
 * @param avatar      头像
 * @param background  背景图片
 * @param color       主题颜色
 * @param email       邮箱
 * @param description 个人简介
 * @param permission  权限
 * @param isPublic    是否公开
 * @param isBan       是否禁用账户
 * @param isVerified  账户是否已经通过邮件验证
 * @param password    密码
 * @param unbanTime   结束禁用账户的时间
 * @param isDeleted   账号是否进行了删除
 */
@Document("accounts")
public record Account(
        @Id @JsonProperty("account_id") @NotNull String accountId,
        @Field("created") @JsonProperty("created") long created,
        @Field("username") @JsonProperty("username") @NotNull String username,
        @Field("nickname") @JsonProperty("nickname") @NotNull String nickname,
        @Field("avatar") @JsonProperty("avatar") @Nullable String avatar,
        @Field("background") @JsonProperty("background") @Nullable String background,
        @Field("color") @JsonProperty("color") @Nullable String color,
        @Field("email") @JsonProperty("email") @NotNull String email,
        @Field("description") @JsonProperty("description") @Nullable String description,
        @Field("permission") @JsonProperty("permission") @NotNull Set<String> permission,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_ban") @JsonProperty("is_ban") boolean isBan,
        @Field("is_verified") @JsonProperty("verified") boolean isVerified,

        // 需要过滤一部分字段的信息
        // 有点笨 什么时候来去掉密码 wq
        @Field("password") @JsonIgnore @NotNull String password,
        @Field("unban_time") @JsonIgnore long unbanTime,

        // 管理功能
        @Field("is_deleted") @JsonIgnore boolean isDeleted
) {
}
