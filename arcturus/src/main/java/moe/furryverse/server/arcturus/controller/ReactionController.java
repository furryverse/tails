package moe.furryverse.server.arcturus.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Reaction;
import moe.furryverse.server.arcturus.service.ReactionService;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class ReactionController {
    final ReactionService reactionService;

    @GetMapping("/post/{postId}/reaction")
    @AccessCheck(access = {Access.REACTION_LIST})
    public Message<?> listReaction(@PathVariable String postId) {
        return Message.success(reactionService.listReaction(postId));
    }

    @GetMapping("/post/{postId}/reaction/{reactionId}")
    @AccessCheck(access = {Access.REACTION_GET})
    public Message<?> getReaction(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String reactionId
    ) {
        return Message.success(reactionService.getReaction(accountId, postId, reactionId));
    }

    @PostMapping("/post/{postId}/reaction")
    @AccessCheck(access = {Access.REACTION_CREATE})
    public Message<?> createReaction(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @RequestBody Reaction reaction
    ) {
        return Message.success(reactionService.createReaction(accountId, postId, reaction));
    }

    @DeleteMapping("/post/{postId}/reaction/{reactionId}")
    @AccessCheck(access = {Access.REACTION_DELETE})
    public Message<?> deleteReaction(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String reactionId
    ) {
        return Message.success(reactionService.deleteReaction(accountId, postId, reactionId));
    }
}
