package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Manageable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

/**
 * 活动
 *
 * @param activityId     活动 ID
 * @param created        创建时间
 * @param createdBy      创建账户
 * @param name           活动名称
 * @param description    描述
 * @param startTime      开始时间
 * @param endTime        结束时间
 * @param cover          封面
 * @param contents       活动内容
 * @param administrators 管理员
 * @param isPublic       是否公开
 * @param isLocked       是否锁定
 * @param isReviewing    是否在审核中
 * @param isDeleted      是否删除
 * @param secret         用于签名验证票据的密钥（用于验票）
 */
@Document("activities")
public record Activity(
        @Id @JsonProperty("activity_id") @NotNull String activityId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("description") @JsonProperty("description") @NotNull String description,
        @Field("start_time") @JsonProperty("start_time") long startTime,
        @Field("end_time") @JsonProperty("end_time") long endTime,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 管理功能
        @Field("administrators") @JsonProperty("administrators") @NotNull Set<String> administrators,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted,
        @Field("secret") @JsonIgnore @NotNull String secret
) implements Attributable, Manageable, Traceable {
}
