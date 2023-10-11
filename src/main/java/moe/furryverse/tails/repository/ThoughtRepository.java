package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Thought;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ThoughtRepository extends MongoRepository<Thought, String> {
    <S extends Thought> @NotNull S save(@NotNull S thought);

    Thought findByPostIdAndThoughtId(String postId, String thoughtId);

    List<Thought> findByPostId(String postId);

    @Query(value = "{ 'post_id': ?0, 'thought_id' : ?1 }")
    Thought updateByPostIdAndThoughtId(String postId, String thoughtId, Thought thought);

    Thought deleteByPostIdAndThoughtId(String postId, String thoughtId);
}
