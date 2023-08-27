package moe.furryverse.server.alnitak.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 星团 对应的是板块 / 分区
 *
 * @param created          创建时间
 * @param clusterId        星团 ID
 * @param name             星团名称
 * @param icon             星团图标
 * @param color            主题颜色
 * @param banner           横幅
 * @param bannerBackground 横幅背景
 * @param background       背景
 * @param description      描述
 * @param reviewer         审核员（可以对帖子进行审核 并决定是否通过）
 * @param administrator    管理员 （可以对帖子进行删除、锁定、置顶、编辑等操作）
 * @param editor           编辑员 （可以对帖子进行编辑）
 * @param viewer           查看员 （在不公开状态下可以查看帖子）
 * @param isPublic         是否公开 （公开的板块可以被所有人查看）
 * @param isLocked         是否锁定 （锁定的板块不允许发帖）
 */
@Document("cluster")
public record Cluster(
        // 基本信息
        @Field("created") long created,
        @Field("cluster_id") @NotNull @Id String clusterId,
        @Field("cluster_name") @NotNull String name,
        @Field("icon") @NotNull String icon,
        @Field("color") @NotNull String color,
        @Field("banner") @Nullable String banner,
        @Field("banner_background") @Nullable String bannerBackground,
        @Field("background") @Nullable String background,
        @Field("description") @Nullable String description,

        // 管理功能
        @Field("reviewer") @NotNull List<String> reviewer,
        @Field("administrator") @NotNull List<String> administrator,
        @Field("editor") @NotNull List<String> editor,
        @Field("viewer") @NotNull List<String> viewer,
        @Field("is_public") boolean isPublic,
        @Field("is_locked") boolean isLocked
) {
}
