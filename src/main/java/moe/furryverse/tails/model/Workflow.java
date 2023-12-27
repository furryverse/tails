package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @param workflowId   工作流 ID
 * @param created      创建时间
 * @param name         工作流名称
 * @param accountId    发起委托的账户 ID
 * @param commissionId 原委托 ID
 * @param orderId      订单 ID
 * @param status       状态
 * @param processing   整个工作流中需要进行的交付次数
 * @param processed    在本次工作流中已经交付的次数
 */
public record Workflow(
        @Id @JsonProperty("workflow_id") @NotNull String workflowId,
        @Field("created") @JsonProperty("created") long created,
        @Field("name") @JsonProperty("name") String name,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("commission_id") @JsonProperty("commission_id") @NotNull String commissionId,
        @Field("order_id") @JsonProperty("order_id") String orderId,
        @Field("status") @JsonProperty("status") WorkflowStatus status,
        @Field("processing") @JsonProperty("processing") int processing,
        @Field("processed") @JsonProperty("processed") int processed
) {
    public enum WorkflowStatus {
        @JsonProperty("padding") PADDING,
        @JsonProperty("started") STARTED,
        @JsonProperty("processing") PROCESSING,
        @JsonProperty("completed") COMPLETED
    }
}
