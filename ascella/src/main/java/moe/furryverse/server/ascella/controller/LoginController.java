package moe.furryverse.server.ascella.controller;

import moe.furryverse.server.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class LoginController {
    @GetMapping("/login/oauth/authorize")
    public Message<?> authorize() {
        return null;
    }

    @GetMapping("/login/oauth/callback")
    public Message<?> callback() {
        return null;
    }
}
