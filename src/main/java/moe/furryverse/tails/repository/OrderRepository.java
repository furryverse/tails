package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderRepository extends MongoRepository<Order, String> {

    @NotNull <S extends Order> S save(@NotNull S entity);

    @NotNull Page<Order> findAll(@NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<Order> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
