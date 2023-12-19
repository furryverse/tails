package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("orders")
public record Order(
    @Field("order_id") @JsonProperty("order_id") String orderId,
    @Field("created") @JsonProperty("created") long created,
    @Field("account_id") @JsonProperty("account_id") String accountId
) {
}
