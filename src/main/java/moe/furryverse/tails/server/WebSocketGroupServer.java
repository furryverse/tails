package moe.furryverse.tails.server;

import jakarta.websocket.server.ServerEndpoint;
import moe.furryverse.tails.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/v0/api/websocket/group")
public class WebSocketGroupServer {
    static GroupService groupService;

    @Autowired
    public void setService(GroupService groupService) {
        WebSocketGroupServer.groupService = groupService;
    }
}
