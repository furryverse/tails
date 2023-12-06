package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Chat;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatRepository extends MongoRepository<Chat, String> {
    <S extends Chat> @NotNull S save(@NotNull S chat);

    Chat findByChatId(String chatId);

    @Query(value = "{ 'chat_id' : ?0 }")
    Chat updateByChatId(String chatId, Chat chat);

    Chat deleteByChatId(String chatId);
}
