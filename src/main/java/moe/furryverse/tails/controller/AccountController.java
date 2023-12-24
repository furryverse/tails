package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.AccountService;
import moe.furryverse.tails.utils.IpUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/account")
public class AccountController {
    final AccountService accountService;

    @PostMapping("/login")
    public Message<?> login(
            HttpServletRequest request,
            @RequestParam(name = "device") String device,
            @RequestParam(name = "identify") String identify,
            @RequestParam(name = "password") String password
    ) {
        return Message.success(
                accountService.login(
                        device, IpUtils.getIp(request), request.getHeader("User-Agent"),
                        identify, password
                )
        );
    }

    @PostMapping("/register")
    public Message<?> register(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {
        return Message.success(accountService.register(email, username, password));
    }
}
