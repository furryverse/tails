package moe.furryverse.tails.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 消息
 *
 * @param chatId   消息唯一 ID
 * @param created  创建时间
 * @param sender   发送人
 * @param receiver 接收人
 * @param contents 消息 Markdown 格式
 */
@Document("chats")
public record Chat(
        @Id @JsonProperty("chat_id") @NotNull String chatId,
        @Field("created") @JsonProperty("created") long created,
        @Field("sender") @JsonProperty("sender") String sender,
        @Field("receiver") @JsonProperty("receiver") String receiver,
        @Field("contents") @JsonProperty("contents") @NotNull List<String> contents
) {
}
