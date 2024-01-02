package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Attributable;
import moe.furryverse.tails.interfaces.Payable;
import moe.furryverse.tails.interfaces.Traceable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 票据 一场活动里会有很多种类型的票据
 *
 * @param ticketId    票据 ID
 * @param created     创建时间
 * @param createdBy   创建该票据的账户 ID
 * @param name        票据名称
 * @param cover       票据封面
 * @param stubCover   票据存根的封面
 * @param description 描述
 * @param price       价格
 * @param stock       数量
 * @param contents    详细介绍内容
 * @param activityId  活动 ID - 与 Activity 的 activity_id 相关联
 * @param isLocked    是否锁定
 * @param isReviewing 是否正在审核
 * @param isDeleted   是否删除
 */
@Document("tickets")
public record Ticket(
        @Id @JsonProperty("ticket_id") @NotNull String ticketId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("cover") @JsonProperty("cover") String cover,
        @Field("stub_cover") @JsonProperty("stub_cover") String stubCover,
        @Field("description") @JsonProperty("description") @NotNull String description,
        @Field("price") @JsonProperty("price") double price,
        @Field("stock") @JsonIgnore int stock,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,

        // 关联键
        @Field("activity_id") @JsonProperty("activity_id") String activityId,

        // 管理功能
        @Field("is_public") @JsonProperty("is_public") boolean isPublic,
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_archived") @JsonProperty("is_archived") boolean isArchived,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable, Attributable, Traceable {
}
