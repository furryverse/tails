package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class LoginController {
    final LoginService loginService;

    @GetMapping("/login/oauth/authorize")
    public Message<?> authorize() {
        return Message.success(
                Map.of("oauth", loginService.getOAuth())
        );
    }

    @GetMapping("/login/oauth/callback")
    public Message<?> callback() {
        return null;
    }
}