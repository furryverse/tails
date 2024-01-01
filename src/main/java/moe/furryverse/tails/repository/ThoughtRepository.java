package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Thought;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ThoughtRepository extends MongoRepository<Thought, String> {
    @NotNull <S extends Thought> S save(@NotNull S entity);

    @Query("{'is_deleted': ?0}")
    @NotNull Page<Thought> findAll(boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'created_by': ?0}")
    @NotNull Page<Thought> findAllByCreatedBy(@NotNull String createdBy, @NotNull Pageable pageable);
}
