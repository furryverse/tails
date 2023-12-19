package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Chat;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<Chat, String> {
    <S extends Chat> @NotNull S save(@NotNull S chat);

    Chat findByChatId(String chatId);

    Chat deleteByChatId(String chatId);
}
