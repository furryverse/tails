package moe.furryverse.tails.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record Token(
        @JsonIgnore String token,
        @JsonIgnore String belong,
        @JsonProperty("access") Set<Access> access,
        @JsonProperty("expire") long expire,
        @JsonProperty("created") long created
) {
}
