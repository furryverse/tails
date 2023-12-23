package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 活动
 *
 * @param activityId     活动 ID
 * @param created        创建时间
 * @param accountId      创建账户
 * @param name           活动名称
 * @param description    描述
 * @param startTime      开始时间
 * @param endTime        结束时间
 * @param cover          封面
 * @param contents       活动内容
 * @param secret         用于签名验证票据的密钥（用于验票）
 * @param administrators 管理员
 */
@Document("activities")
public record Activity(
        @Id @JsonProperty("activity_id") @NotNull String activityId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("name") @JsonProperty("name") @NotNull String name,
        @Field("description") @JsonProperty("description") @NotNull String description,
        @Field("start_time") @JsonProperty("start_time") long startTime,
        @Field("end_time") @JsonProperty("end_time") long endTime,
        @Field("cover") @JsonProperty("cover") @Nullable String cover,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents,
        @Field("secret") @JsonIgnore @NotNull String secret,
        @Field("administrators") @JsonProperty("administrators") @NotNull List<String> administrators
) {
}
