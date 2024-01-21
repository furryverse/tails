package moe.furryverse.tails.dto;

public record LoginDto(
    String device,
    String identify,
    String password
) { }
