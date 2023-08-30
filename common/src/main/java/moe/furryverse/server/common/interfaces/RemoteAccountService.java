package moe.furryverse.server.common.interfaces;

import moe.furryverse.server.common.model.Account;

public interface RemoteAccountService {
    Account getAccountByAccountId(String accountId);

    Account getAccountByUsername(String username);
}
