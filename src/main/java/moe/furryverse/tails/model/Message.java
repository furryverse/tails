package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 消息
 *
 * @param messageId 消息唯一 ID
 * @param created   创建时间
 * @param sender    发送人
 * @param receiver  接收人
 * @param messages  消息 Markdown 格式
 */
@Document("messages")
public record Message(
        @Field("message_id") @JsonProperty("message_id") String messageId,
        @Field("created") @JsonProperty("created") long created,
        @Field("sender") @JsonProperty("sender") String sender,
        @Field("receiver") @JsonProperty("receiver") String receiver,
        @Field("message") @JsonProperty("message") List<String> message
) {
}
