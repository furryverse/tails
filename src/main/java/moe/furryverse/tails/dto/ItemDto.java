package moe.furryverse.tails.dto;

import java.util.List;
import java.util.Set;

public record ItemDto(
        String name,
        String cover,
        Set<String> tags,
        List<String> shows,
        List<String> contents,
        int price,
        int stock,
        boolean isPublic
) {
}
