package moe.furryverse.tails.model;

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
 * 店铺
 *
 * @param shopId        店铺 ID
 * @param created       创建时间
 * @param createdBy     创建的账户
 * @param name          店铺名称
 * @param cover         店铺封面
 * @param tags          标签
 * @param contents      详细介绍内容
 * @param administrator 管理员
 * @param isLocked      是否锁定
 * @param isReviewing   是否审核
 * @param isDeleted     是否删除
 */
@Document("shops")
public record Shop(
        @Id @JsonProperty("shop_id") @NotNull String shopId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("tags") @JsonProperty("tags") @NotNull List<String> tags,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 管理功能
        @Field("administrators") @JsonProperty("administrators") @Nullable Set<String> administrators,
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Attributable, Manageable, Traceable {
}
