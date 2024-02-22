package moe.furryverse.tails.dto;

import java.util.List;

public record TicketDto(
        String name,
        String cover,
        String stubCover,
        String description,
        Double price,
        Integer stock,
        List<String> contents
) {
}
