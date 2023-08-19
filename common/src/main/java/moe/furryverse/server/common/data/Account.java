package moe.furryverse.server.common.data;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("account")
@AllArgsConstructor
public class Account {
    // 创建时间
    long created;
    // 用户唯一用户名
    String username;
    // 昵称
    String nickname;
    // 用户唯一 ID
    String uid;
    // 头像
    String avatar;
    // 背景图片
    String background;
    // 主题颜色
    String color;
    // 邮箱
    String email;
    // 个人简介
    String description;
}
