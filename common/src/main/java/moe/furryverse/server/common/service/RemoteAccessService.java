package moe.furryverse.server.common.service;

import moe.furryverse.server.common.security.Access;

import java.util.List;

public interface RemoteAccessService {
    boolean check(String token, String accountId, List<Access> access);

    boolean check(String token, String accountId, Access access);
}
