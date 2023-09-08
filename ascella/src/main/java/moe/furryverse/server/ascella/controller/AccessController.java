package moe.furryverse.server.ascella.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.ascella.service.AccessService;
import moe.furryverse.server.ascella.service.AccountService;
import moe.furryverse.server.ascella.service.LoginService;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class AccessController {
    final AccessService accessService;
    final AccountService accountService;
    final LoginService loginService;

    /**
     * 校验权限
     *
     * @param accountId        请求的账户 ID
     * @param access           请求的权限
     * @return 是否有权限
     */
    @PostMapping("/access")
    public Message<?> access(
            @RequestHeader(value = Resource.CustomHeader.AUTHORIZE_HEADER) String token,
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @RequestBody List<Access> access
    ) {
        return Message.success(
                Map.of("access", accessService.check(token, accountId, access))
        );
    }
}
