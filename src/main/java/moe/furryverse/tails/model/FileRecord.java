package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 文件记录
 *
 * @param fileId    文件 ID
 * @param created   创建时间
 * @param accountId 用户账户 ID
 * @param name      文件名
 * @param url       文件 URL
 * @param type      文件类型
 * @param size      文件大小
 * @param isPublic  是否公开
 */
@Document("file_records")
public record FileRecord(
        @Id @JsonProperty("file_id") @NotNull String fileId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("url") @JsonProperty("url") @NotNull String url,
        @Field("type") @JsonProperty("type") @NotNull String type,
        @Field("size") @JsonProperty("size") long size,

        // 管理
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) {
}
