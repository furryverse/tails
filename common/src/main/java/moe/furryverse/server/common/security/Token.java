package moe.furryverse.server.common.security;

import java.util.List;

public record Token(
        String token,
        String belong,
        List<Access> access,
        long expire,
        long created
) {
}
