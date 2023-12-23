package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Chat;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
    @NotNull <S extends Chat> S save(@NotNull S entity);

    Chat findChatByChatId(String chatId);

    Chat deleteChatByChatId(String chatId);
}
