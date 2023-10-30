package moe.furryverse.tails.server;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import moe.furryverse.tails.annotation.WebSocketCheck;
import moe.furryverse.tails.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/v0/api/websocket/{fromId}/chat/{toId}}")
public class WebSocketChatServer {
    static ChatService chatService;

    @Autowired
    public void setService(ChatService chatService) {
        WebSocketChatServer.chatService = chatService;
    }

    @OnOpen
    @WebSocketCheck(access = {})
    public void onOpen(Session session, @PathParam("fromId") String fromId, @PathParam("toId") String toId) {
        // checked token from query
        String query = session.getQueryString();
    }
}
