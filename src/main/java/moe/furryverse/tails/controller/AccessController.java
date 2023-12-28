package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/access")
public class AccessController {
    @GetMapping("/verify/email")
    public Message<?> verifyEmail() {
        return Message.success(null);
    }

    @GetMapping("/key")
    public Message<?> security() {
        return Message.success(SecurityUtils.publicKey());
    }
}
