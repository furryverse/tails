/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.annotation.PermissionCheck;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.dto.CommentDto;
import moe.furryverse.tails.dto.PostDto;
import moe.furryverse.tails.dto.ReactionDto;
import moe.furryverse.tails.dto.ThoughtDto;
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
    public Object listPost(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return postService.listPost(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                page,
                size
        );
    }

    @PostMapping()
    @PermissionCheck(access = {Permission.POST_WRITE})
    public Object createPost(@RequestBody PostDto post) {
        return postService.createPost(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                post.title(),
                post.background(),
                post.categoryId()
        );
    }

    @GetMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_READ}, requiredLogin = false)
    public Object readPost(@PathVariable String postId) {
        return postService.readPost(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId
        );
    }

    @PostMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Object updatePost(@PathVariable String postId, @RequestBody PostDto post) {
        return postService.updatePost(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                post.title(),
                post.background(),
                post.categoryId()
        );
    }

    @DeleteMapping("/{postId}")
    @PermissionCheck(access = {Permission.POST_DELETE})
    public Object deletePost(@PathVariable String postId) {
        return postService.deletePost(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId
        );
    }

    //////////////////////////////////////////////////////////////// Tag
    @PostMapping("/{postId}/tag/{tagId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Object addTag(@PathVariable String postId, @PathVariable String tagId) {
        return postService.addTag(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                tagId
        );
    }

    @DeleteMapping("/{postId}/tag/{tagId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Object deleteTag(@PathVariable String postId, @PathVariable String tagId) {
        return postService.removeTag(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                tagId
        );
    }

    //////////////////////////////////////////////////////////////// Viewer

    @PostMapping("/{postId}/viewer/{viewerId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Object addViewer(@PathVariable String postId, @PathVariable String viewerId) {
        return postService.addViewer(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                viewerId
        );
    }

    @DeleteMapping("/{postId}/viewer/{viewerId}")
    @PermissionCheck(access = {Permission.POST_UPDATE})
    public Object deleteViewer(@PathVariable String postId, @PathVariable String viewerId) {
        return postService.removeViewer(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                viewerId
        );
    }

    //////////////////////////////////////////////////////////////// Collaborator

    @PermissionCheck(access = {Permission.POST_UPDATE})
    @PostMapping("/{postId}/collaborator/{collaboratorId}")
    public Object addCollaborator(@PathVariable String postId, @PathVariable String collaboratorId) {
        return postService.addCollaborator(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                collaboratorId
        );
    }

    @PermissionCheck(access = {Permission.POST_UPDATE})
    @DeleteMapping("/{postId}/collaborator/{collaboratorId}")
    public Object deleteCollaborator(@PathVariable String postId, @PathVariable String collaboratorId) {
        return postService.removeCollaborator(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                collaboratorId
        );
    }

    //////////////////////////////////////////////////////////////// Comment

    @GetMapping("/{postId}/comment")
    @PermissionCheck(access = {Permission.POST_COMMENT_LIST}, requiredLogin = false)
    public Object listComment(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return postService.listComment(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                page,
                size
        );
    }

    @PostMapping("/{postId}/comment")
    @PermissionCheck(access = {Permission.POST_COMMENT_WRITE})
    public Object createComment(@PathVariable String postId, @RequestBody CommentDto comment) {
        return postService.createComment(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                comment.contents()
        );
    }

    @GetMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_READ}, requiredLogin = false)
    public Object readComment(@PathVariable String postId, @PathVariable String commentId) {
        return postService.readComment(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                commentId
        );
    }

    @PostMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_UPDATE})
    public Object updateComment(
            @PathVariable String postId,
            @PathVariable String commentId,
            @RequestBody CommentDto comment
    ) {
        return postService.updateComment(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                commentId,
                comment.contents()
        );
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    @PermissionCheck(access = {Permission.POST_COMMENT_DELETE})
    public Object deleteComment(@PathVariable String postId, @PathVariable String commentId) {
        return postService.deleteComment(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                commentId
        );
    }

    //////////////////////////////////////////////////////////////// Thought

    @GetMapping("/{postId}/thought")
    @PermissionCheck(access = {Permission.POST_THOUGHT_LIST}, requiredLogin = false)
    public Object listThought(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return postService.listThought(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                page,
                size
        );
    }

    @PostMapping("/{postId}/thought")
    @PermissionCheck(access = {Permission.POST_THOUGHT_WRITE})
    public Object createThought(@PathVariable String postId, @RequestBody ThoughtDto thought) {
        return postService.createThought(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                thought.contents()
        );
    }

    @GetMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_READ}, requiredLogin = false)
    public Object readThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return postService.readThought(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                thoughtId
        );
    }

    @PostMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_UPDATE})
    public Object updateThought(
            @PathVariable String postId,
            @PathVariable String thoughtId,
            @RequestBody ThoughtDto thought
    ) {
        return postService.updateThought(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                thoughtId,
                thought.contents()
        );
    }

    @DeleteMapping("/{postId}/thought/{thoughtId}")
    @PermissionCheck(access = {Permission.POST_THOUGHT_DELETE})
    public Object deleteThought(@PathVariable String postId, @PathVariable String thoughtId) {
        return postService.deleteThought(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                thoughtId
        );
    }

    //////////////////////////////////////////////////////////////// Reaction

    @GetMapping("/{postId}/reaction")
    @PermissionCheck(access = {Permission.POST_REACTION_LIST}, requiredLogin = false)
    public Object listReaction(
            @PathVariable String postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return postService.listReaction(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                page,
                size
        );
    }

    @PostMapping("/{postId}/reaction")
    @PermissionCheck(access = {Permission.POST_REACTION_WRITE})
    public Object createReaction(@PathVariable String postId, @RequestBody ReactionDto reaction) {
        return postService.createReaction(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                reaction.emoji(),
                reaction.content()
        );
    }

    @GetMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_READ}, requiredLogin = false)
    public Object readReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return postService.readReaction(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                reactionId
        );
    }

    @DeleteMapping("/{postId}/reaction/{reactionId}")
    @PermissionCheck(access = {Permission.POST_REACTION_DELETE})
    public Object deleteReaction(@PathVariable String postId, @PathVariable String reactionId) {
        return postService.deleteReaction(
                (String) request.getAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER),
                postId,
                reactionId
        );
    }
}
