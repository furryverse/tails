package moe.furryverse.server.arcturus.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Thought;
import moe.furryverse.server.arcturus.service.ThoughtService;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class ThoughtController {
    final ThoughtService thoughtService;

    @GetMapping("/post/{postId}/thought")
    @AccessCheck(access = {Access.THOUGHT_LIST})
    public Message<?> listThought(@PathVariable String postId) {
        return Message.success(thoughtService.listThought(postId));
    }

    @GetMapping("/post/{postId}/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_GET})
    public Message<?> getThought(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String thoughtId
    ) {
        return Message.success(thoughtService.getThought(accountId, postId, thoughtId));
    }

    @PostMapping("/post/{postId}/thought")
    @AccessCheck(access = {Access.THOUGHT_CREATE})
    public Message<?> createThought(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @RequestBody Thought thought
    ) {
        return Message.success(thoughtService.createThought(accountId, postId, thought));
    }

    @PutMapping("/post/{postId}/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_UPDATE})
    public Message<?> updateThought(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String thoughtId,
            @RequestBody Thought thought
    ) {
        return Message.success(thoughtService.updateThought(accountId, postId, thoughtId, thought));
    }

    @DeleteMapping("/post/{postId}/thought/{thoughtId}")
    @AccessCheck(access = {Access.THOUGHT_DELETE})
    public Message<?> deleteThought(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String thoughtId
    ) {
        return Message.success(thoughtService.deleteThought(accountId, postId, thoughtId));
    }
}
