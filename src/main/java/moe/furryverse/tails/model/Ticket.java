package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("tickets")
public record Ticket(
        @Field("ticket_id") @JsonProperty("ticket_id") String ticketId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("order_id") @JsonProperty("order_id") String orderId,
        @Field("start_time") @JsonProperty("start_time") long startTime,
        @Field("end_time") @JsonProperty("end_time") long endTime,
        @Field("is_used") @JsonProperty("is_used") boolean isUsed,
        @Field("used_at") @JsonProperty("used_at") long usedAt
) {
}
