package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Post;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class PostController {
    @GetMapping("/post")
    @AccessCheck(access = {Access.POST_LIST})
    public Message<?> listPost() {
        return null;
    }

    @GetMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_GET})
    public Message<?> getPost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId
    ) {
        return null;
    }

    @PostMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_CREATE})
    public Message<?> createPost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @RequestBody Post post
    ) {
        return null;
    }

    @PutMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_UPDATE})
    public Message<?> updatePost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @RequestBody Post post
    ) {
        return null;
    }

    @DeleteMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_DELETE})
    public Message<?> deletePost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId
    ) {
        return null;
    }
}
