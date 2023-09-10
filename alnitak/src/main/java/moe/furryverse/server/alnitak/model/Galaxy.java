package moe.furryverse.server.alnitak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 星系 对应的是帖子 / 主题
 *
 * @param created       创建时间
 * @param galaxyId      星系 ID
 * @param clusterId     星团 ID
 * @param title         主题标题
 * @param titleEdited   主题标题（编辑历史）
 * @param background    背景
 * @param nucleus       星核（主题标签）
 * @param viewers       未公开状态下可以查看帖子的用户
 * @param collaborators 可以编辑帖子的用户
 * @param isPublic      是否公开
 * @param isLocked      是否锁定（不允许查看不允许修改）
 * @param isReviewing   是否正在审核
 * @param isArchived    是否已归档（不允许修改）
 */
@Document("galaxies")
public record Galaxy(
        // 基本信息
        @Field("created") @JsonProperty("created") long created,
        @Field("galaxy_id") @JsonProperty("galaxy_id") @NotNull @Id String galaxyId,
        @Field("cluster_id") @JsonProperty("cluster_id") @NotNull String clusterId,
        @Field("title") @JsonProperty("title") @NotNull String title,
        @Field("title_edited") @JsonProperty("title_edited") @NotNull String titleEdited,
        @Field("background") @JsonProperty("background") @Nullable String background,
        @Field("nucleus") @JsonProperty("nucleus") @NotNull List<String> nucleus,

        // 管理功能
        @Field("viewers") @JsonProperty("viewers") @NotNull List<String> viewers,
        @Field("collaborators") @JsonProperty("collaborators") @NotNull List<String> collaborators,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived
) {
}
