package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.service.IndexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/index")
public class IndexController {
    final HttpServletRequest request;
    final IndexService indexService;

    @GetMapping
    public Object search() {
        return null;
    }

    @GetMapping("/recommendation/shop")
    public Object recommendingShop() {
        return null;
    }

    @GetMapping("/recommendation/item")
    public Object recommendingItem() {
        return null;
    }

    @GetMapping("/recommendation/novel")
    public Object recommendingNovel() {
        return null;
    }

    @GetMapping("/recommendation/commission")
    public Object recommendingCommission() {
        return null;
    }

    @GetMapping("/recommendation/user")
    public Object recommendingUser() {
        return null;
    }

    @GetMapping("/recommendation/keyword")
    public Object recommendingKeyword() {
        return null;
    }
}
