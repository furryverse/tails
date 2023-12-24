package moe.furryverse.tails.controller;

import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/security")
public class SecurityController {
    @GetMapping("/key")
    public Message<?> security() {
        return Message.success(SecurityUtils.publicKey());
    }

    @GetMapping("/verify/email")
    public Message<?> verifyEmail() {
        return Message.success(null);
    }
}
