package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import moe.furryverse.tails.interfaces.Payable;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 委托
 *
 * @param commissionId 委托 ID
 * @param created      创建时间
 * @param createdBy    账号
 * @param name         委托名称
 * @param contents     详细内容
 * @param price        价格
 * @param processing   需要进行的交付次数
 * @param isLocked     是否锁定
 * @param isReviewing  是否正在审核
 * @param isDeleted    是否删除
 */
public record Commission(
        @Id @JsonProperty("commission_id") @NotNull String commissionId,
        @Field("created") @JsonProperty("created") long created,
        @Field("created_by") @JsonProperty("created_by") @NotNull String createdBy,
        @Field("name") @JsonProperty("name") String name,
        @Field("contents") @JsonProperty("contents") List<String> contents,
        @Field("price") @JsonProperty("price") double price,
        @Field("processing") @JsonProperty("processing") int processing,

        // 管理功能
        @Field("is_locked") @JsonProperty("is_locked") boolean isLocked,
        @Field("is_reviewing") @JsonProperty("is_reviewing") boolean isReviewing,
        @Field("is_deleted") @JsonProperty("is_deleted") boolean isDeleted
) implements Payable {
}
