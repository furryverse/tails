package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Chapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
    @NotNull <S extends Chapter> S save(@NotNull S entity);

    @NotNull Page<Chapter> findAll(@NotNull Pageable pageable);

    @Query("account_id = ?0")
    @NotNull Page<Chapter> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
