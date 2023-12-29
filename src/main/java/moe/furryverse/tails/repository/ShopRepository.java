package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Shop;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ShopRepository extends MongoRepository<Shop, String> {
    @NotNull <S extends Shop> S save(@NotNull S entity);

    @Query("{'account_id': ?0}")
    @NotNull Page<Shop> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
