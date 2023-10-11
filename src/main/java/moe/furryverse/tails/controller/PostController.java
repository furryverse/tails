package moe.furryverse.tails.controller;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.AccessCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.model.Post;
import moe.furryverse.tails.security.Access;
import moe.furryverse.tails.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class PostController {
    final PostService postService;

    @GetMapping("/post")
    @AccessCheck(access = {Access.POST_LIST})
    public Message<?> listPost(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size
    ) {
        return Message.success(postService.listPost(page, size));
    }

    @GetMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_GET})
    public Message<?> getPost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId
    ) {
        return Message.success(postService.getPost(accountId, postId));
    }

    @PostMapping("/post")
    @AccessCheck(access = {Access.POST_CREATE})
    public Message<?> createPost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @RequestBody Post post
    ) {
        return Message.success(postService.createPost(accountId, post));
    }

    @PutMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_UPDATE})
    public Message<?> updatePost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId,
            @RequestBody Post post
    ) {
        return Message.success(postService.updatePost(accountId, postId, post));
    }

    @DeleteMapping("/post/{postId}")
    @AccessCheck(access = {Access.POST_DELETE})
    public Message<?> deletePost(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String postId
    ) {
        return Message.success(postService.deletePost(accountId, postId));
    }
}
