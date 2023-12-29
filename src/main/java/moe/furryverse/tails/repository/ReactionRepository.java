package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Reaction;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ReactionRepository extends MongoRepository<Reaction, String> {
    @NotNull <S extends Reaction> S save(@NotNull S entity);

    @Query("{'account_id': ?0}")
    @NotNull Page<Reaction> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
