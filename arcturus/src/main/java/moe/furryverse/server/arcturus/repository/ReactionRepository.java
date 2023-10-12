package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Reaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
    <S extends Reaction> @NotNull S save(@NotNull S reaction);

    Reaction findByPostIdAndReactionId(String postId, String reactionId);

    List<Reaction> findByPostId(String postId);

    Reaction deleteByPostIdAndReactionId(String postId, String reactionId);
}
