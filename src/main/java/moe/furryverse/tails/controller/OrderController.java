package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.model.Order;
import moe.furryverse.tails.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/order")
public class OrderController {
    final OrderService orderService;

    @GetMapping()
    public Message<?> listOrder(String accountId) {
        return Message.success(orderService.listOrder(accountId, 0, 10));
    }

    @GetMapping("/{orderId}")
    public Message<?> getOrder(String accountId, @PathVariable String orderId) {
        return Message.success(orderService.getOrder(accountId, orderId));
    }

    @GetMapping("/cancel")
    public Message<?> cancelOrder(String accountId, String orderId) {
        return Message.success(orderService.cancelOrder(accountId, orderId));
    }

    @PostMapping()
    public Message<?> createOrder(String accountId, String bindId, Order.OrderType type) {
        return Message.success(orderService.createOrder(accountId, bindId, type));
    }
}
