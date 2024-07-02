/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.utils.IpUtils;
import moe.furryverse.tails.server.dto.LoginDto;
import moe.furryverse.tails.server.dto.RegisterDto;
import moe.furryverse.tails.server.service.AccountService;
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
