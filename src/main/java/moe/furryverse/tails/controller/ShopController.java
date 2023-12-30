package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.ShopService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/shop")
public class ShopController {
    final HttpServletRequest request;
    final ShopService shopService;

    //////////////////////////////////////////////////////////////// Shop

    @GetMapping()
    public Message<?> listShop() {
        return Message.success();
    }

    @PostMapping()
    public Message<?> createShop() {
        return Message.success();
    }

    @PostMapping("/{shopId}")
    public Message<?> updateShop(@PathVariable String shopId) {
        return Message.success();
    }

    @GetMapping("/{shopId}")
    public Message<?> getShop(@PathVariable String shopId) {
        return Message.success();
    }

    @DeleteMapping("/{shopId}")
    public Message<?> deleteShop(@PathVariable String shopId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Item

    @GetMapping("/{shopId}/item")
    public Message<?> listShopItem(@PathVariable String shopId) {
        return Message.success();
    }

    @PostMapping("/{shopId}/item")
    public Message<?> createShopItem(@PathVariable String shopId) {
        return Message.success();
    }

    @PostMapping("/{shopId}/item/{itemId}")
    public Message<?> updateShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return Message.success();
    }

    @GetMapping("/{shopId}/item/{itemId}")
    public Message<?> getShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return Message.success();
    }

    @DeleteMapping("/{shopId}/item/{itemId}")
    public Message<?> deleteShopItem(@PathVariable String shopId, @PathVariable String itemId) {
        return Message.success();
    }
}
