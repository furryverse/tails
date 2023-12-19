package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("shops")
public record Shop(
        @Field("shop_id") @JsonProperty("shop_id") String shopId,
        @Field("created") @JsonProperty("created") long created,
        @Field("account_id") @JsonProperty("account_id") String accountId
) {
}
