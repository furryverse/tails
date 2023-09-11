package moe.furryverse.server.arcturus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 小行星 对应的是具体到某个主题贴或者回复贴的一行文字的单评
 *
 * @param created    创建时间
 * @param asteroidId 小行星 ID
 * @param galaxyId   星系 ID
 * @param accountId  账户 ID
 * @param contents   内容
 */
@Document("asteroids")
public record Asteroid(
        @Field("created") @JsonProperty("created") long created,
        @Field("asteroid_id") @JsonProperty("asteroid_id") @NotNull @Id String asteroidId,
        @Field("galaxy_id") @JsonProperty("galaxy_id") @NotNull String galaxyId,
        @Field("account_id") @JsonProperty("account_id") @NotNull String accountId,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents
) {
}
