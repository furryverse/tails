package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.dto.LoginDto;
import moe.furryverse.tails.dto.RegisterDto;
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
    public Object login(@RequestBody LoginDto login) {
        return accountService.login(
                login.device(),
                IpUtils.getIp(request),
                request.getHeader("User-Agent"),
                login.identify(),
                login.password()
        );
    }

    @PostMapping("/register")
    public Object register(@RequestBody RegisterDto register) {
        return accountService.register(
                register.email(),
                register.username(),
                register.password()
        );
    }
}
