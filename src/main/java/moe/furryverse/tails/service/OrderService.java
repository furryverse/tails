package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Order;
import moe.furryverse.tails.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;

    public List<Order> listOrder(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE)
        );
        Page<Order> orders = orderRepository.findAllByAccountId(accountId, pageable);

        return orders.getContent();
    }

    public Order getOrder(String accountId, String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order createOrder(String accountId, String bindId, Order.OrderType type) {
        return null;
    }

    public Order cancelOrder(String accountId, String orderId) {
        return null;
    }

    public Order changedStatus(String orderId, Order.OrderStatus status) {
        return null;
    }
}
