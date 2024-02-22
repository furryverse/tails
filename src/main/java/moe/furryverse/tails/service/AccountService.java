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

package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Account;
import moe.furryverse.tails.repository.AccountRepository;
import moe.furryverse.tails.security.Role;
import moe.furryverse.tails.security.Token;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.SecurityUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    final AccessService accessService;
    final AccountRepository accountRepository;

    public Token login(String device, String ip, String useragent, String identify, String password) {
        Account account = accountRepository.existsByUsername(identify)
                ? accountRepository.findByUsername(identify)
                : accountRepository.findByEmail(identify);

        if (account == null) throw new NotFoundDataException("account is not found.", null, null, null);

        String decrypt = SecurityUtils.decrypt(password);
        if (Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8().matches(decrypt, account.password())) {
            return accessService.create(device, ip, useragent, account.accountId(), account.permission());
        }

        return null;
    }

    public Account register(String email, String username, String password) {
        // 检查账号是否存在
        if (accountRepository.existsByUsername(username)) return null;
        if (accountRepository.existsByEmail(username)) return null;

        String decrypt = SecurityUtils.decrypt(password);
        String hash = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8().encode(decrypt);
        Account account = new Account(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                username,
                username,
                null,
                null,
                null,
                email,
                null,
                Role.allPermissions(),
                false,
                false,
                false,
                hash,
                0,
                false
        );

        return accountRepository.save(account);
    }
}
