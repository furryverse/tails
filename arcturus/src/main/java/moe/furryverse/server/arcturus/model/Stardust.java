package moe.furryverse.server.arcturus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 星尘 对应的是具体到某个主题贴或者回复贴的 Diff 信息
 *
 * @param created    创建时间
 * @param stardustId 星尘 ID
 * @param bindId     绑定的行星 ID
 * @param diff       内容 Diff 信息
 * @param accountId  修改人账户 ID
 */
@SuppressWarnings("SpellCheckingInspection")
@Document("stardusts")
public record Stardust(
        @Field("created") @JsonProperty("created") long created,
        @Field("stardust_id") @JsonProperty("stardust_id") String stardustId,
        @Field("bind_id") @JsonProperty("bind_id") String bindId,
        @Field("diff") @JsonProperty("diff") String diff,
        @Field("account_id") @JsonProperty("account_id") String accountId
) {
}
