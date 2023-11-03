package moe.furryverse.tails.chat;

public record Chat(
        WebSocketChatServer from,
        WebSocketChatServer to
) {
}
