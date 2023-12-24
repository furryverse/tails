package moe.furryverse.tails.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record Token(
        @JsonProperty("token") String token,
        @JsonProperty("belong") String belong,
        @JsonProperty("access") Set<String> access,
        @JsonProperty("expire") long expire,
        @JsonProperty("created") long created
) {
}
