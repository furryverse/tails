package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Thought;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThoughtRepository extends MongoRepository<Thought, String> {
    @NotNull <S extends Thought> S save(@NotNull S entity);

    Thought findThoughtByThoughtId(String thoughtId);

    Thought deleteThoughtByThoughtId(String thoughtId);
}
