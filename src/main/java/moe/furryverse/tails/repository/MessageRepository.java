package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Message;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MessageRepository extends MongoRepository<Message, String> {
    <S extends Message> @NotNull S save(@NotNull S message);

    Message findByMessageId(String messageId);

    @Query(value = "{ 'message_id' : ?0 }")
    Message updateByMessageId(String messageId, Message message);

    Message deleteByMessageId(String messageId);
}
