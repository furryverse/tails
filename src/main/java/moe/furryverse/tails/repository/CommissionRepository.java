package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Commission;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CommissionRepository extends MongoRepository<Commission, String> {
    @NotNull <S extends Commission> S save(@NotNull S commission);

    @NotNull Page<Commission> findAll(@NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<Commission> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
