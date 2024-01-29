package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/post")
public class PostController {
    final HttpServletRequest request;
    final PostService postService;

    //////////////////////////////////////////////////////////////// Post

    @GetMapping()
    @PermissionCheck(access = {Permission.POST_LIST}, requiredLogin = false)
    public Message<?> listPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(
                postService.listPost(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        page,
                        size
                )
        );
    }

    @PostMapping()
    @PermissionCheck(access = {Permission.POST_WRITE})
    public Message<?> createPost() {
        return Message.success();
    }

    @GetMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_READ}, requiredLogin = false)
    public Message<?> getPost(@PathVariable String postId) {
        return Message.success();
    }

    @PostMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Message<?> updatePost(@PathVariable String postId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_DELETE})
    public Message<?> deletePost(@PathVariable String postId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Comment

    @GetMapping("/{postId}/comment")
    @PermissionCheck(access = {Permission.POST_COMMENT_LIST}, requiredLogin = false)
    public Message<?> listComment(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(
                postService.listComment(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        page,
                        size
                )
        );
    }

    @PostMapping("/{postId}/comment")
    @PermissionCheck(access = {Permission.POST_COMMENT_WRITE})
    public Message<?> createComment(@PathVariable String postId) {
        return Message.success();
    }

    @GetMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_READ}, requiredLogin = false)
    public Message<?> getComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success();
    }

    @PostMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_UPDATE})
    public Message<?> updateComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_DELETE})
    public Message<?> deleteComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Thought

    @GetMapping("/{postId}/thought")
    @PermissionCheck(access = {Permission.POST_THOUGHT_LIST}, requiredLogin = false)
    public Message<?> listThought(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(
                postService.listThought(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        page,
                        size
                )
        );
    }

    @PostMapping("/{postId}/thought")
    @PermissionCheck(access = {Permission.POST_THOUGHT_WRITE})
    public Message<?> createThought(@PathVariable String postId) {
        return Message.success();
    }

    @GetMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_READ}, requiredLogin = false)
    public Message<?> getThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success();
    }

    @PostMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_UPDATE})
    public Message<?> updateThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_DELETE})
    public Message<?> deleteThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Reaction

    @GetMapping("/{postId}/reaction")
    @PermissionCheck(access = {Permission.POST_REACTION_LIST}, requiredLogin = false)
    public Message<?> listReaction(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return Message.success(
                postService.listReaction(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        page,
                        size
                )
        );
    }

    @PostMapping("/{postId}/reaction")
    @PermissionCheck(access = {Permission.POST_REACTION_WRITE})
    public Message<?> createReaction(@PathVariable String postId) {
        return Message.success();
    }

    @GetMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_READ}, requiredLogin = false)
    public Message<?> getReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success();
    }

    @PostMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_UPDATE})
    public Message<?> updateReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_DELETE})
    public Message<?> deleteReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success();
    }
}
