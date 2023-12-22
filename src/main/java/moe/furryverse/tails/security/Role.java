package moe.furryverse.tails.security;

import lombok.Getter;
import lombok.experimental.Accessors;
import moe.furryverse.tails.utils.ReflectionUtils;

import java.util.Set;

public class Role {
    @Getter
    @Accessors(fluent = true)
    static Set<String> allPermissions;

    static {
        try {
            allPermissions = ReflectionUtils.getAllStaticPublicStrings(Permission.class);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("unable to get all permissions", e);
        }
    }
}
