package moe.furryverse.server.ascella.controller;

import moe.furryverse.server.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class AccountController {
    @GetMapping("/account")
    public Message<?> account() {
        return null;
    }

    @GetMapping("/account/:id")
    public Message<?> accountById() {
        return null;
    }

    @PostMapping("/account/:id")
    public Message<?> updateAccount() {
        return null;
    }
}
