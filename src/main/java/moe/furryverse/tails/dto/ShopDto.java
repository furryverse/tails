package moe.furryverse.tails.dto;

import java.util.List;
import java.util.Set;

public record ShopDto(
        String name,
        String cover,
        Set<String> tags,
        List<String> contents,
        boolean isPublic
) {
}
