package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.message.Message;
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
    public Message<?> listPost() {
        return Message.success();
    }

    @PostMapping()
    public Message<?> createPost() {
        return Message.success();
    }

    @GetMapping("/{postId}")
    public Message<?> getPost(@PathVariable String postId) {
        return Message.success();
    }

    @PostMapping("/{postId}")
    public Message<?> updatePost(@PathVariable String postId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}")
    public Message<?> deletePost(@PathVariable String postId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Comment

    @GetMapping("/{postId}/comment")
    public Message<?> listComment(@PathVariable String postId) {
        return Message.success();
    }

    @PostMapping("/{postId}/comment")
    public Message<?> createComment(@PathVariable String postId) {
        return Message.success();
    }

    @GetMapping("/{postId}/comment/{commentId}")
    public Message<?> getComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success();
    }

    @PostMapping("/{postId}/comment/{commentId}")
    public Message<?> updateComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public Message<?> deleteComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Thought

    @GetMapping("/{postId}/thought")
    public Message<?> listThought(@PathVariable String postId) {
        return Message.success();
    }

    @PostMapping("/{postId}/thought")
    public Message<?> createThought(@PathVariable String postId) {
        return Message.success();
    }

    @GetMapping("/{postId}/thought/{thoughtId}")
    public Message<?> getThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success();
    }

    @PostMapping("/{postId}/thought/{thoughtId}")
    public Message<?> updateThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}/thought/{thoughtId}")
    public Message<?> deleteThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success();
    }

    //////////////////////////////////////////////////////////////// Reaction

    @GetMapping("/{postId}/reaction")
    public Message<?> listReaction(@PathVariable String postId) {
        return Message.success();
    }

    @PostMapping("/{postId}/reaction")
    public Message<?> createReaction(@PathVariable String postId) {
        return Message.success();
    }

    @GetMapping("/{postId}/reaction/{reactionId}")
    public Message<?> getReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success();
    }

    @PostMapping("/{postId}/reaction/{reactionId}")
    public Message<?> updateReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success();
    }

    @DeleteMapping("/{postId}/reaction/{reactionId}")
    public Message<?> deleteReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success();
    }
}
