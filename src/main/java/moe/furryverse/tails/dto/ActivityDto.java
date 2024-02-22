package moe.furryverse.tails.dto;

import java.util.List;

public record ActivityDto(
        String name,
        String description,
        Long startTime,
        Long endTime,
        String cover,
        List<String> contents,
        String secret
) {
}
