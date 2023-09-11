package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Reaction;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class ReactionController {
    @GetMapping("/reaction")
    @AccessCheck(access = {Access.REACTION_LIST})
    public Message<?> listReaction() {
        return null;
    }

    @GetMapping("/reaction/{reactionId}")
    @AccessCheck(access = {Access.REACTION_GET})
    public Message<?> getReaction(@PathVariable String reactionId) {
        return null;
    }

    @PostMapping("/reaction/{reactionId}")
    @AccessCheck(access = {Access.REACTION_CREATE})
    public Message<?> createReaction(@PathVariable String reactionId, @RequestBody Reaction reaction) {
        return null;
    }

    @PutMapping("/reaction/{reactionId}")
    @AccessCheck(access = {Access.REACTION_UPDATE})
    public Message<?> updateReaction(@PathVariable String reactionId, @RequestBody Reaction reaction) {
        return null;
    }

    @DeleteMapping("/reaction/{reactionId}")
    @AccessCheck(access = {Access.REACTION_DELETE})
    public Message<?> deleteReaction(@PathVariable String reactionId) {
        return null;
    }
}
