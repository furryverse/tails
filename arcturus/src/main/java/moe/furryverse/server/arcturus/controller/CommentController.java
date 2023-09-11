package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Comment;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class CommentController {
    @GetMapping("/comment")
    @AccessCheck(access = {Access.COMMENT_LIST})
    public Message<?> listComment() {
        return null;
    }

    @GetMapping("/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_GET})
    public Message<?> getComment(@PathVariable String commentId) {
        return null;
    }

    @PostMapping("/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_CREATE})
    public Message<?> createComment(@PathVariable String commentId, @RequestBody Comment comment) {
        return null;
    }

    @PutMapping("/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_UPDATE})
    public Message<?> updateComment(@PathVariable String commentId, @RequestBody Comment comment) {
        return null;
    }

    @DeleteMapping("/comment/{commentId}")
    @AccessCheck(access = {Access.COMMENT_DELETE})
    public Message<?> deleteComment(@PathVariable String commentId) {
        return null;
    }
}
