/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.server.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.interfaces.Payable;
import moe.furryverse.tails.common.model.Order;
import moe.furryverse.tails.server.config.PageConfiguration;
import moe.furryverse.tails.server.repository.*;
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
        Page<Order> orders = orderRepository.findAllByCreatedBy(accountId, pageable);

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
                record.createdBy().equals(accountId) &&
                        record.status() != Order.OrderStatus.PAID &&
                        record.status() != Order.OrderStatus.SUCCESS
        ) {
            return orderRepository.save(new Order(
                    record.orderId(),
                    record.created(),
                    record.createdBy(),
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
                record.createdBy(),
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
