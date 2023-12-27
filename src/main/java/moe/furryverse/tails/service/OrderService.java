package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.interfaces.Payable;
import moe.furryverse.tails.model.Order;
import moe.furryverse.tails.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    final OrderRepository orderRepository;

    // Index Source Data Id
    final ItemRepository itemRepository;
    final NovelRepository novelRepository;
    final TicketRepository ticketRepository;
    final ChapterRepository chapterRepository;
    final CommissionRepository commissionRepository;

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
        Payable payable = getPayable(bindId, type);


        return null;
    }

    private Payable getPayable(String bindId, Order.OrderType type) {
        return switch (type) {
            case NOVEL -> novelRepository.findById(bindId).orElse(null);
            case CHAPTER -> chapterRepository.findById(bindId).orElse(null);
            case TICKET -> ticketRepository.findById(bindId).orElse(null);
            case ITEM -> itemRepository.findById(bindId).orElse(null);
            case COMMISSION -> commissionRepository.findById(bindId).orElse(null);
        };
    }

    public Order cancelOrder(String accountId, String orderId) {
        Order record = orderRepository.findById(orderId).orElse(null);
        if (record == null) return null;

        if (
                record.accountId().equals(accountId) &&
                        record.status() != Order.OrderStatus.PAID &&
                        record.status() != Order.OrderStatus.SUCCESS
        ) {
            return orderRepository.save(new Order(
                    record.orderId(),
                    record.created(),
                    record.accountId(),
                    record.name(),
                    record.sellPrice(),
                    record.buyPrice(),
                    Order.OrderStatus.CANCELLED,
                    record.type(),
                    record.bindId()
            ));
        }

        return record;
    }

    public Order changedStatus(String orderId, Order.OrderStatus status) {
        Order record = orderRepository.findById(orderId).orElse(null);
        if (record == null) return null;

        Order order = new Order(
                record.orderId(),
                record.created(),
                record.accountId(),
                record.name(),
                record.sellPrice(),
                record.buyPrice(),
                status == null ? record.status() : status,
                record.type(),
                record.bindId()
        );

        return orderRepository.save(order);
    }
}
