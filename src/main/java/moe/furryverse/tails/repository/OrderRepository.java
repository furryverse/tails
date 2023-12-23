package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    @NotNull <S extends Order> S save(@NotNull S entity);

    Order findOrderByOrderId(String orderId);

    Order deleteOrderByOrderId(String orderId);
}
