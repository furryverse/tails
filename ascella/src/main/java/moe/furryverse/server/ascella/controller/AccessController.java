package moe.furryverse.server.ascella.controller;

import moe.furryverse.server.common.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
public class AccessController {
    @PostMapping("/access")
    public Message<?> access() {
        return null;
    }
}
