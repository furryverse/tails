package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 文件记录
 *
 * @param fileId      文件 ID
 * @param created     创建时间
 * @param createdBy   用户账户 ID
 * @param name        文件名
 * @param url         文件 URL
 * @param type        文件类型
 * @param size        文件大小
 * @param isPublic    是否公开
 * @param isLocked    是否锁定
 * @param isReviewing 是否在审核中
 * @param isDeleted   是否删除
 */
@Document("file_records")
public record FileRecord(
        @Id @JsonProperty("file_id") @NotNull String fileId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("url") @JsonProperty("url") @NotNull String url,
        @Field("type") @JsonProperty("type") @NotNull String type,
        @Field("size") @JsonProperty("size") long size,

        // 管理功能
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) {
}
