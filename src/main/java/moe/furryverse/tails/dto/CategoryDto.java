package moe.furryverse.tails.dto;

public record CategoryDto(
        String id,
        String categoryName,
        String icon,
        String color,
        String banner,
        String bannerBackground,
        String background,
        String description,
        boolean isPublic
) {
}
