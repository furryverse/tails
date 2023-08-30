package moe.furryverse.server.common.service;

import moe.furryverse.server.common.model.Account;

public interface RemoteAccountService {
    Account getAccountByAccountId(String accountId);

    Account getAccountByUsername(String username);
}
