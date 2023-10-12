package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.data.Session;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.model.Account;
import moe.furryverse.tails.service.AccessService;
import moe.furryverse.tails.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class AccountController {
    final AccountService accountService;
    final AccessService accessService;

    @GetMapping("/account")
    public Message<?> account(
            @RequestParam(value = "username", required = false) String username,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER, required = false) String accountId
    ) {
        return Message.success(
                Map.of(
                        "account",
                        username == null ?
                                accountService.getAccountByAccountId(accountId) :
                                accountService.getAccountByUsername(username)
                )
        );
    }

    @GetMapping("/account/{id}")
    public Message<?> accountById(
            @PathVariable String id,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER, required = false) String accountId
    ) {
        Account account = accountService.getAccountByAccountId(id);

        // 非公开账户，且请求者不是该账户的所有者
        if ((!account.isPublic()) && (!Objects.equals(accountId, id))) {
            throw new NotFoundDataException(Message.ReturnMessage.NOT_FOUND, "/account/" + id, "GET", accountId);
        }

        return Message.success(Map.of("account", account));
    }

    @PostMapping("/account/{id}")
    public Message<?> updateAccount(
            @PathVariable String id,
            @RequestBody Account account,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId
    ) {
        if (!Objects.equals(id, accountId)) {
            throw new UnauthorizationException(Message.ReturnMessage.UNAUTHORIZED, "/account/" + id, "POST", accountId);
        }

        return Message.success(Map.of("account", accountService.updateAccount(id, account)));
    }

    @GetMapping("/account/{id}/session")
    public Message<?> session(
            @PathVariable String id,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId
    ) {
        if (!Objects.equals(id, accountId)) {
            throw new UnauthorizationException(Message.ReturnMessage.UNAUTHORIZED, "/account/" + id + "/session", "GET", accountId);
        }

        List<Session> sessions = accessService.getSession(id);

        return Message.success(Map.of("sessions", sessions));
    }

    @DeleteMapping("/account/{id}/session")
    public Message<?> deleteSession(
            @PathVariable String id,
            @RequestHeader(value = Resource.CustomHeader.AUTHORIZE_HEADER) String token,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId
    ) {
        if (!Objects.equals(id, accountId)) {
            throw new UnauthorizationException(Message.ReturnMessage.UNAUTHORIZED, "/account/" + id + "/session", "DELETE", accountId);
        }

        Session session = accessService.revokeSession(token);
        if (session == null) {
            throw new NotFoundDataException(Message.ReturnMessage.NOT_FOUND, "/account/" + id + "/session", "DELETE", accountId);
        }

        return Message.success(
                Map.of(
                        "token", token,
                        "session", session
                )
        );
    }
}