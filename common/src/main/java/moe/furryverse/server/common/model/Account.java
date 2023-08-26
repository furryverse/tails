package moe.furryverse.server.common.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
 * @param isPublic    是否公开
 */
@Document("account")
public record Account(
        @Field("account_id") @NotNull @Id String accountId,
        @Field("created") long created,
        @Field("username") @NotNull String username,
        @Field("nickname") @NotNull String nickname,
        @Field("avatar") @Nullable String avatar,
        @Field("background") @Nullable String background,
        @Field("color") @Nullable String color,
        @Field("email") @NotNull String email,
        @Field("description") @Nullable String description,
        @Field("is_public") boolean isPublic
) {
}
