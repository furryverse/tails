package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Comet;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class CometController {
    @GetMapping("/comet")
    @AccessCheck(access = {Access.COMET_LIST})
    public Message<?> listComet() {
        return null;
    }

    @GetMapping("/comet/{cometId}")
    @AccessCheck(access = {Access.COMET_GET})
    public Message<?> getComet(@PathVariable String cometId) {
        return null;
    }

    @PostMapping("/comet/{cometId}")
    @AccessCheck(access = {Access.COMET_CREATE})
    public Message<?> createComet(@PathVariable String cometId, @RequestBody Comet comet) {
        return null;
    }

    @PutMapping("/comet/{cometId}")
    @AccessCheck(access = {Access.COMET_UPDATE})
    public Message<?> updateComet(@PathVariable String cometId, @RequestBody Comet comet) {
        return null;
    }

    @DeleteMapping("/comet/{cometId}")
    @AccessCheck(access = {Access.COMET_DELETE})
    public Message<?> deleteComet(@PathVariable String cometId) {
        return null;
    }
}
