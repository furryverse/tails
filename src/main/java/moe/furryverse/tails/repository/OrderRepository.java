package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    @NotNull <S extends Order> S save(@NotNull S entity);
    @NotNull Page<Order> findAllByAccountId(String accountId, Pageable pageable);
}
