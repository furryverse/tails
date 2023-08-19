package moe.furryverse.server.common.service;

import java.util.List;

public interface RemoteAuthorizationService {
    boolean hasAccess(String uid, String token, List<String> access);
}
