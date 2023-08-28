package moe.furryverse.server.ascella.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.ascella.repository.AccountRepository;
import moe.furryverse.server.ascella.repository.OAuthRepository;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.model.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class AccountController {
    @GetMapping("/account")
    public Message<?> account(
            @RequestParam(value = "username", required = false) String username,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER, required = false) String accountId
    ) {
        return null;
    }

    @GetMapping("/account/{id}")
    public Message<?> accountById(
            @PathVariable String id,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER, required = false) String accountId
    ) {
        return null;
    }

    @PostMapping("/account/{id}")
    public Message<?> updateAccount(
            @PathVariable String id,
            @RequestBody Account account,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId
    ) {
        return null;
    }

    @GetMapping("/account/{id}/session")
    public Message<?> session(
            @PathVariable String id,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId
    ) {
        return null;
    }

    @DeleteMapping("/account/{id}/session/{sessionId}")
    public Message<?> deleteSession(
            @PathVariable String id,
            @PathVariable String sessionId,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountIdInHeader
    ) {
        return null;
    }
}
