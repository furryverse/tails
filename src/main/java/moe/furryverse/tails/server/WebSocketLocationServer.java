package moe.furryverse.tails.server;

import jakarta.websocket.server.ServerEndpoint;
import moe.furryverse.tails.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/v0/api/websocket/location")
public class WebSocketLocationServer {
    static LocationService locationService;

    @Autowired
    public void setService(LocationService locationService) {
        WebSocketLocationServer.locationService = locationService;
    }
}
