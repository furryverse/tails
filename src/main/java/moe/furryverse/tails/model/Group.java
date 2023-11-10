package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/**
 * 一个群组
 *
 * @param groupId      群聊唯一 ID
 * @param created      创建时间
 * @param name         群聊名称
 * @param accountId    创建人
 * @param descriptions 群聊介绍
 * @param members      群聊成员
 * @param admins       群聊管理
 * @param nicks        昵称列表
 * @param awards       称号列表
 */
@Document("groups")
public record Group(
        @Field("group_id") @JsonProperty("group_id") String groupId,
        @Field("created") @JsonProperty("created") long created,
        @Field("name") @JsonProperty("name") String name,
        @Field("account_id") @JsonProperty("account_id") String accountId,
        @Field("description") @JsonProperty("description") List<String> descriptions,
        @Field("members") @JsonProperty("members") List<String> members,
        @Field("admins") @JsonProperty("admins") List<String> admins,
        @Field("nicks") @JsonProperty("nicks") Map<String, String> nicks,
        @Field("awards") @JsonProperty("awards") Map<String, String> awards
) {
}
