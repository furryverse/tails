package moe.furryverse.tails.chat;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import moe.furryverse.tails.service.MessageService;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/api/v0/websocket/{from_id}/chat/{to_id}")
public class WebSocketChatServer {
    MessageService messageService;

    public WebSocketChatServer(MessageService messageService) {
        this.messageService = messageService;
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason) {

    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("from_id") Long formId, @PathParam("to_id") Long toId) {

    }

    @OnError
    public void onError(Throwable throwable) {

    }
}