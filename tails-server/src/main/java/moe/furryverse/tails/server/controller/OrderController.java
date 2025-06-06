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

package moe.furryverse.tails.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.content.Resource;
import moe.furryverse.tails.common.model.Order;
import moe.furryverse.tails.server.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/order")
public class OrderController {
    final HttpServletRequest request;
    final OrderService orderService;

    @GetMapping()
    public Object listOrder() {
        return orderService.listOrder(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                0,
                10
        );
    }

    @PostMapping()
    public Object createOrder(String bindId, Order.OrderType type) {
        return orderService.createOrder(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                bindId,
                type
        );
    }

    @GetMapping("/{orderId}")
    public Object getOrder(@PathVariable String orderId) {
        return orderService.getOrder(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                orderId
        );
    }

    @DeleteMapping("/{orderId}")
    public Object cancelOrder(@PathVariable String orderId) {
        return orderService.cancelOrder(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                orderId
        );
    }
}
