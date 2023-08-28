package moe.furryverse.server.ascella.data;

import moe.furryverse.server.common.security.Token;
import org.jetbrains.annotations.NotNull;

public record Session(
        @NotNull Token token,
        @NotNull String name,
        @NotNull String ip,
        @NotNull String useragent
) {
}
