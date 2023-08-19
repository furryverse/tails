package moe.furryverse.server.common.service;

import moe.furryverse.server.common.data.Account;

public interface RemoteAccountService {
    Account getAccountByUid(String uid);

    Account getAccountByUsername(String username);

    Account getAccountByEmail(String email);
}
