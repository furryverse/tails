package moe.furryverse.tails.dto;

import java.util.List;

public record ChapterDto(
        String name,
        List<String> contents,
        boolean isPublic,
        boolean isDraft
) {
}
