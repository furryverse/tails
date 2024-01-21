package moe.furryverse.tails.dto;

public record RegisterDto(
    String email,
    String username,
    String password
) { }
