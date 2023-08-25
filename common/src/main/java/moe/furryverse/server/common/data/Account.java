package moe.furryverse.server.common.data;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("account")
@AllArgsConstructor
public class Account {
    // 唯一 ID
    @NotNull @Id String id;
    // 创建时间
    long created;
    // 用户唯一用户名
    @NotNull String username;
    // 昵称
    @NotNull String nickname;
    // 用户唯一 ID
    @NotNull String uid;
    // 头像
    @Nullable String avatar;
    // 背景图片
    @Nullable String background;
    // 主题颜色
    @Nullable String color;
    // 邮箱
    @NotNull String email;
    // 个人简介
    @Nullable String description;
}
