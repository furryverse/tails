package moe.furryverse.server.arcturus.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Comment;
import moe.furryverse.server.arcturus.service.CommentService;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class CommentController {
    final CommentService commentService;

    @GetMapping("/post/{postId}/comment")
    @AccessCheck(access = {Access.COMMENT_LIST})
    public Message<?> listComment(@PathVariable String postId) {
        return Message.success(commentService.listComment(postId));
    }

    @GetMapping("/post/{postId}/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_GET})
    public Message<?> getComment(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String commentId
    ) {
        return Message.success(commentService.getComment(accountId, postId, commentId));
    }

    @PostMapping("/post/{postId}/comment")
    @AccessCheck(access = {Access.COMMENT_CREATE})
    public Message<?> createComment(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @RequestBody Comment comment
    ) {
        return Message.success(commentService.createComment(accountId, postId, comment));
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_UPDATE})
    public Message<?> updateComment(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String commentId,
            @RequestBody Comment comment
    ) {
        return Message.success(commentService.updateComment(postId, commentId, comment));
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_DELETE})
    public Message<?> deleteComment(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @PathVariable String commentId
    ) {
        return Message.success(commentService.deleteComment(accountId, postId, commentId));
    }
}
