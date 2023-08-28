package moe.furryverse.server.common.security;

import java.util.Set;

public record Token(
        String token,
        String belong,
        Set<Access> access,
        long expire,
        long created
) {
}
