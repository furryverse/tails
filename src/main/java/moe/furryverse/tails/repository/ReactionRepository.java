package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Reaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
    @NotNull <S extends Reaction> S save(@NotNull S entity);

    Reaction findReactionByReactionId(String reactionId);

    Reaction deleteReactionByReactionId(String reactionId);
}
