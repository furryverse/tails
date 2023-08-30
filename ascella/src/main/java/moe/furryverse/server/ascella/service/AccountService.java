package moe.furryverse.server.ascella.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.ascella.repository.AccountRepository;
import moe.furryverse.server.ascella.repository.OAuthRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.interfaces.RemoteAccountService;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.model.Account;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@DubboService
@RequiredArgsConstructor
public class AccountService implements RemoteAccountService {
    final AccountRepository accountRepository;
    final OAuthRepository oAuthRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountByAccountId(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new NotFoundDataException(
                    Message.ExceptionMessage.NOT_FOUND_ACCOUNT_WITH_ID,
                    "account service calling",
                    "GET",
                    accountId
            );
        }

        return account;
    }

    public Account getAccountByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new NotFoundDataException(
                    Message.ExceptionMessage.NOT_FOUND_ACCOUNT_WITH_USERNAME,
                    "account service calling",
                    "GET",
                    username
            );
        }

        return account;
    }

    public Account updateAccount(String accountId, Account account) {
        Account oldAccount = accountRepository.findByAccountId(accountId);
        if (oldAccount == null) {
            throw new NotFoundDataException(Message.ExceptionMessage.NOT_FOUND_ACCOUNT_WITH_ID, "account service calling", "PUT", accountId);
        }

        Account newAccount = new Account(
                oldAccount.accountId(),
                oldAccount.created(),
                account.username(),
                account.nickname(),
                account.avatar(),
                account.background() != null ? account.background() : oldAccount.background(),
                account.color() != null ? account.color() : oldAccount.color(),
                account.email(),
                account.description() != null ? account.description() : oldAccount.description(),
                account.isPublic(),
                account.isBan(),
                oldAccount.unbanTime(),
                oldAccount.isDeleted()
        );

        return accountRepository.updateByAccountId(accountId, newAccount);
    }
}
