package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/access")
public class AccessController {
    @GetMapping("/verify/email")
    public Object verifyEmail() {
        return null;
    }

    @GetMapping("/key")
    public Object security() {
        return SecurityUtils.publicKey();
    }
}
