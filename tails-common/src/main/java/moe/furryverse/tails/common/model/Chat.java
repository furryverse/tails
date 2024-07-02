/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.common.model;

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
