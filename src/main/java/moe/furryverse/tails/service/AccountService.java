package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Account;
import moe.furryverse.tails.repository.AccountRepository;
import moe.furryverse.tails.security.Token;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.SecurityUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AccountService {
    final AccountRepository accountRepository;

    public Token login(String identify, String password) {
        Account account = accountRepository.existsByUsername(identify)
                ? accountRepository.findByUsername(identify)
                : accountRepository.findByEmail(identify);

        if (account == null) throw new NotFoundDataException("account is not found.", null, null, null);

        String decrypt = SecurityUtils.decrypt(password);
        if (Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8().matches(decrypt, account.password())) {
            return new Token(
                    RandomUtils.uuid(),
                    account.accountId(),
                    new HashSet<>(),
                    0,
                    TimeUtils.getMilliUnixTime()
            );
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
