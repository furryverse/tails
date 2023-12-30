package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.model.Order;
import moe.furryverse.tails.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/order")
public class OrderController {
    final HttpServletRequest request;
    final OrderService orderService;

    @GetMapping()
    public Message<?> listOrder() {
        return Message.success(
                orderService.listOrder(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        0,
                        10)
        );
    }

    @PostMapping()
    public Message<?> createOrder(String bindId, Order.OrderType type) {
        return Message.success(
                orderService.createOrder(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        bindId,
                        type
                )
        );
    }

    @GetMapping("/{orderId}")
    public Message<?> getOrder(@PathVariable String orderId) {
        return Message.success(
                orderService.getOrder(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        orderId
                )
        );
    }

    @DeleteMapping("/{orderId}")
    public Message<?> cancelOrder(@PathVariable String orderId) {
        return Message.success(
                orderService.cancelOrder(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        orderId
                )
        );
    }
}
