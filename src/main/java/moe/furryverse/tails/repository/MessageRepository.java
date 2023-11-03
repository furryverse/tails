package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
