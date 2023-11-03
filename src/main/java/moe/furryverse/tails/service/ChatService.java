package moe.furryverse.tails.service;

import moe.furryverse.tails.chat.WebSocketChatServer;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatService {
    static Map<String, WebSocketChatServer> chats = new ConcurrentHashMap<>();

    public WebSocketChatServer connectionChat(String accountId, WebSocketChatServer server) {
        return chats.put(accountId, server);
    }

    public WebSocketChatServer disconnectionChat(String accountId) {
        return chats.remove(accountId);
    }

    public boolean isConnectionChat(String accountId) {
        return chats.containsKey(accountId);
    }

    public WebSocketChatServer getChat(String accountId) {
        return chats.get(accountId);
    }
}
