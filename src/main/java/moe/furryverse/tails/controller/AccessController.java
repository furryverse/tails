package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Access;
import moe.furryverse.tails.service.AccessService;
import moe.furryverse.tails.service.AccountService;
import moe.furryverse.tails.service.LoginService;
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
     * @param access 请求的权限
     * @return 是否有权限
     */
    @PostMapping("/access")
    public Message<?> access(
            @RequestHeader(value = Resource.CustomHeader.AUTHORIZE_HEADER) String authorization,
            @RequestBody List<Access> access
    ) {
        String token = authorization.substring(Resource.CustomHeader.AUTHORIZE_HEADER_PREFIX.length());

        return Message.success(
                Map.of("access", accessService.check(token, access))
        );
    }
}