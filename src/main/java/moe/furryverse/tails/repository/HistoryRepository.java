package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.History;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface HistoryRepository extends MongoRepository<History, String> {
    @NotNull <S extends History> S save(@NotNull S entity);

    @NotNull Page<History> findAll(@NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<History> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
