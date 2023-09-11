package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Thought;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ThoughtRepository extends MongoRepository<Thought, String> {
    <S extends Thought> @NotNull S save(@NotNull S thought);

    Thought findByThoughtId(String thoughtId);

    List<Thought> findByPostId(String postId);

    @Query(value = "{ 'thought_id' : ?0 }")
    Thought updateByThoughtId(String thoughtId, Thought thought);

    Thought deleteByThoughtId(String thoughtId);
}
