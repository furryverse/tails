package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Reaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
    <S extends Reaction> @NotNull S insert(@NotNull S reaction);

    Reaction findByPostIdAndReactionId(String postId, String reactionId);

    List<Reaction> findByPostId(String postId);

    Reaction deleteByPostIdAndReactionId(String postId, String reactionId);
}
