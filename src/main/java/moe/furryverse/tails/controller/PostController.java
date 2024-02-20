package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Permission;
import moe.furryverse.tails.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Message<?> createPost(String title, String background, String categoryId) {
        return Message.success(
                postService.createPost(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        title,
                        background,
                        categoryId
                )
        );
    }

    @GetMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_READ}, requiredLogin = false)
    public Message<?> readPost(@PathVariable String postId) {
        return Message.success(
                postService.readPost(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId
                )
        );
    }

    @PostMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Message<?> updatePost(@PathVariable String postId, String title, String background, String categoryId) {
        return Message.success(
                postService.updatePost(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        title,
                        background,
                        categoryId
                )
        );
    }

    @DeleteMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_DELETE})
    public Message<?> deletePost(@PathVariable String postId) {
        return Message.success(
                postService.deletePost(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId
                )
        );
    }

    //////////////////////////////////////////////////////////////// Tag
    @PostMapping("/{postId}/tag/{tagId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Message<?> addTag(@PathVariable String postId, @PathVariable String tagId) {
        return Message.success(
                postService.addTag(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        tagId
                )
        );
    }

    @DeleteMapping("/{postId}/tag/{tagId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Message<?> deleteTag(@PathVariable String postId, @PathVariable String tagId) {
        return Message.success(
                postService.removeTag(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        tagId
                )
        );
    }

    //////////////////////////////////////////////////////////////// Viewer

    @PostMapping("/{postId}/viewer/{viewerId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Message<?> addViewer(@PathVariable String postId, @PathVariable String viewerId) {
        return Message.success(
                postService.addViewer(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        viewerId
                )
        );
    }

    @DeleteMapping("/{postId}/viewer/{viewerId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Message<?> deleteViewer(@PathVariable String postId, @PathVariable String viewerId) {
        return Message.success(
                postService.removeViewer(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        viewerId
                )
        );
    }

    //////////////////////////////////////////////////////////////// Collaborator

    @PermissionCheck(access = {Permission.POST_UPDATE})
    @PostMapping("/{postId}/collaborator/{collaboratorId}")
    public Message<?> addCollaborator(@PathVariable String postId, @PathVariable String collaboratorId) {
        return Message.success(
                postService.addCollaborator(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        collaboratorId
                )
        );
    }

    @PermissionCheck(access = {Permission.POST_UPDATE})
    @DeleteMapping("/{postId}/collaborator/{collaboratorId}")
    public Message<?> deleteCollaborator(@PathVariable String postId, @PathVariable String collaboratorId) {
        return Message.success(
                postService.removeCollaborator(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        collaboratorId
                )
        );
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
    public Message<?> createComment(@PathVariable String postId, List<String> contents) {
        return Message.success(
                postService.createComment(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        contents
                )
        );
    }

    @GetMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_READ}, requiredLogin = false)
    public Message<?> readComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success(
                postService.readComment(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        commentId
                )
        );
    }

    @PostMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_UPDATE})
    public Message<?> updateComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success(
                postService.updateComment(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        commentId
                )
        );
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_DELETE})
    public Message<?> deleteComment(@PathVariable String postId, @PathVariable String commentId) {
        return Message.success(
                postService.deleteComment(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        commentId
                )
        );
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
    public Message<?> createThought(@PathVariable String postId, List<String> contents) {
        return Message.success(
                postService.createThought(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        contents
                )
        );
    }

    @GetMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_READ}, requiredLogin = false)
    public Message<?> readThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success(
                postService.readThought(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        thoughtId
                )
        );
    }

    @PostMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_UPDATE})
    public Message<?> updateThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success(
                postService.updateThought(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        thoughtId
                )
        );
    }

    @DeleteMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_DELETE})
    public Message<?> deleteThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return Message.success(
                postService.deleteThought(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        thoughtId
                )
        );
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
    public Message<?> createReaction(@PathVariable String postId, String emoji, String content) {
        return Message.success(
                postService.createReaction(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        emoji,
                        content
                )
        );
    }

    @GetMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_READ}, requiredLogin = false)
    public Message<?> readReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success(
                postService.readReaction(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        reactionId
                )
        );
    }

    @DeleteMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_DELETE})
    public Message<?> deleteReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return Message.success(
                postService.deleteReaction(
                        (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                        postId,
                        reactionId
                )
        );
    }
}
