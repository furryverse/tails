package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/index")
public class IndexController {
    @GetMapping("/recommendation/shop")
    public Message<?> recommendingShop() {
        return Message.success();
    }

    @GetMapping("/recommendation/item")
    public Message<?> recommendingItem() {
        return Message.success();
    }

    @GetMapping("/recommendation/user")
    public Message<?> recommendingUser() {
        return Message.success();
    }

    @GetMapping("/recommendation/keyword")
    public Message<?> recommendingKeyword() {
        return Message.success();
    }

    @GetMapping("/recommendation/novel")
    public Message<?> recommendingNovel() {
        return Message.success();
    }
}
