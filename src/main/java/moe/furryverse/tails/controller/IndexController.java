package moe.furryverse.tails.controller;

import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class IndexController {
    @GetMapping({"/", "/api/v0", "/version", "/api/v0/version"})
    public Message<?> getEntry() {
        return Message.success(
                new HashMap<>() {{
                    put("name", Resource.Info.NAME);
                    put("version", Resource.Info.VERSION);
                    put("open_source_license", Resource.Info.OPEN_SOURCE_LICENSE);
                    put("open_source_repo", Resource.Info.OPEN_SOURCE_REPO);
                }}
        );
    }
}
