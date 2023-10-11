package moe.furryverse.tails.data;

import moe.furryverse.tails.security.Token;
import org.jetbrains.annotations.NotNull;

public record Session(
        @NotNull Token token,
        @NotNull String name,
        @NotNull String ip,
        @NotNull String useragent
) {
}
