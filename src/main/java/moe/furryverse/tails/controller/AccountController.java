package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.dto.LoginDto;
import moe.furryverse.tails.dto.RegisterDto;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.AccountService;
import moe.furryverse.tails.utils.IpUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/account")
public class AccountController {
    final HttpServletRequest request;
    final AccountService accountService;

    @PostMapping("/login")
    public Message<?> login(@RequestBody LoginDto login) {
        return Message.success(
                accountService.login(
                        login.device(),
                        IpUtils.getIp(request),
                        request.getHeader("User-Agent"),
                        login.identify(),
                        login.password()
                )
        );
    }

    @PostMapping("/register")
    public Message<?> register(@RequestBody RegisterDto register) {
        return Message.success(
                accountService.register(
                        register.email(),
                        register.username(),
                        register.password()
                )
        );
    }
}
