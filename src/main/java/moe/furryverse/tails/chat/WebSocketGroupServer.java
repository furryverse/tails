package moe.furryverse.tails.chat;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import moe.furryverse.tails.service.GroupService;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/api/v0/websocket/group/{group_id}")
public class WebSocketGroupServer {
    GroupService groupService;

    public WebSocketGroupServer(GroupService groupService) {
        this.groupService = groupService;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {

    }

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("group_id") Long groupId) {

    }

    @OnError
    public void onError(Throwable throwable) {

    }
}
