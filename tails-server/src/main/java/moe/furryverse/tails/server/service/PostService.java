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

package moe.furryverse.tails.server.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.common.model.Comment;
import moe.furryverse.tails.common.model.Post;
import moe.furryverse.tails.common.model.Reaction;
import moe.furryverse.tails.common.model.Thought;
import moe.furryverse.tails.common.utils.ManageStatusUtils;
import moe.furryverse.tails.common.utils.RandomUtils;
import moe.furryverse.tails.common.utils.TimeUtils;
import moe.furryverse.tails.server.config.PageConfiguration;
import moe.furryverse.tails.server.repository.CommentRepository;
import moe.furryverse.tails.server.repository.PostRepository;
import moe.furryverse.tails.server.repository.ReactionRepository;
import moe.furryverse.tails.server.repository.ThoughtRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostService {
    final PostRepository postRepository;
    final ThoughtRepository thoughtRepository;
    final CommentRepository commentRepository;
    final ReactionRepository reactionRepository;

    public List<Post> listPost(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Post> posts = accountId == null
                ? postRepository.findAll(false, false, false, pageable)
                : postRepository.findAllByCreatedBy(accountId, false, pageable);

        return posts.getContent();
    }

    public Post createPost(
            String accountId,
            String title, String background, String categoryId
    ) {
        Post post = new Post(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                title,
                background,
                categoryId,
                Set.of(),
                Set.of(),
                Set.of(),
                true,
                false,
                false,
                true,
                false
        );

        return postRepository.save(post);
    }

    public Post readPost(String accountId, String postId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkReadStatus(post, accountId);

        return post;
    }

    public Post updatePost(
            String accountId, String postId,
            String title, String background, String categoryId
    ) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                title == null ? post.title() : title,
                background == null ? post.background() : background,
                categoryId == null ? post.categoryId() : categoryId,
                post.tags(),
                post.viewers(),
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);
    }

    public Post deletePost(String accountId, String postId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(post, accountId);

        Post deleted = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                post.tags(),
                post.viewers(),
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                true
        );

        return postRepository.save(deleted);
    }

    public Post addTag(String accountId, String postId, String tag) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Set<String> currentTags = new HashSet<>() {{
            addAll(post.tags());
            add(tag);
        }};

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                currentTags,
                post.viewers(),
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);
    }

    public Post removeTag(String accountId, String postId, String tag) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        if (tag == null || post.tags().isEmpty()) {
            return post;
        }

        Set<String> tags = new HashSet<>(post.tags());
        tags.remove(tag);

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                tags,
                post.viewers(),
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);
    }

    public Post addViewer(String accountId, String postId, String viewerId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Set<String> currentViewers = new HashSet<>() {{
            addAll(post.viewers());
            add(viewerId);
        }};

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                post.tags(),
                currentViewers,
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);
    }

    public Post removeViewer(String accountId, String postId, String viewerId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        if (viewerId == null || post.viewers().isEmpty()) {
            return post;
        }

        Set<String> viewers = new HashSet<>(post.viewers());
        viewers.remove(viewerId);

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                post.tags(),
                viewers,
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);
    }

    public Post addCollaborator(String accountId, String postId, String collaboratorId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Set<String> currentCollaborators = new HashSet<>() {{
            addAll(post.collaborators());
            add(collaboratorId);
        }};

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                post.tags(),
                post.viewers(),
                currentCollaborators,
                post.isPublic(),

                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);

    }

    public Post removeCollaborator(String accountId, String postId, String collaboratorId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        if (collaboratorId == null || post.collaborators().isEmpty()) {
            return post;
        }

        Set<String> collaborators = new HashSet<>(post.collaborators());
        collaborators.remove(collaboratorId);

        Post updated = new Post(
                post.postId(),
                post.created(),
                post.createdBy(),
                post.title(),
                post.background(),
                post.categoryId(),
                post.tags(),
                post.viewers(),
                collaborators,
                post.isPublic(),
                post.isLocked(),
                post.isArchived(),
                post.isReviewing(),
                post.isDeleted()
        );

        return postRepository.save(updated);
    }

    public List<Thought> listThought(String accountId, String postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Thought> thoughts = accountId == null
                ? thoughtRepository.findAll(false, false, false, postId, pageable)
                : thoughtRepository.findAllByCreatedBy(accountId, false, postId, pageable);

        return thoughts.getContent();
    }

    public Thought createThought(
            String accountId,
            String postId,
            List<String> contents
    ) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Thought thought = new Thought(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                contents,
                postId,
                true,
                false,
                false,
                true,
                false
        );

        return thoughtRepository.save(thought);
    }

    public Thought readThought(String accountId, String postId, String thoughtId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkReadStatus(post, accountId);

        Thought thought = thoughtRepository.findById(thoughtId).orElse(null);
        ManageStatusUtils.checkReadStatus(thought, accountId);

        return thought;
    }

    public Thought updateThought(String accountId, String postId, String thoughtId, List<String> contents) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Thought thought = thoughtRepository.findById(thoughtId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(thought, accountId);

        Thought updated = new Thought(
                thought.thoughtId(),
                thought.created(),
                thought.createdBy(),
                contents,
                thought.postId(),
                thought.isPublic(),
                thought.isLocked(),
                thought.isArchived(),
                thought.isReviewing(),
                thought.isDeleted()
        );

        return thoughtRepository.save(updated);
    }

    public Thought deleteThought(String accountId, String postId, String thoughtId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Thought thought = thoughtRepository.findById(thoughtId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(thought, accountId);

        Thought deleted = new Thought(
                thought.thoughtId(),
                thought.created(),
                thought.createdBy(),
                thought.contents(),
                thought.postId(),
                thought.isPublic(),
                thought.isLocked(),
                thought.isArchived(),
                thought.isReviewing(),
                true
        );

        return thoughtRepository.save(deleted);
    }

    public List<Reaction> listReaction(String accountId, String postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Reaction> reactions = accountId == null
                ? reactionRepository.findAll(false, false, false, postId, pageable)
                : reactionRepository.findAllByCreatedBy(accountId, false, postId, pageable);

        return reactions.getContent();
    }

    public Reaction createReaction(
            String accountId,
            String postId,
            String emoji,
            String content
    ) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Reaction reaction = new Reaction(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                emoji,
                content,
                postId,
                true,
                false,
                false,
                true,
                false
        );

        return reactionRepository.save(reaction);
    }

    public Reaction readReaction(String accountId, String postId, String reactionId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkReadStatus(post, accountId);

        Reaction reaction = reactionRepository.findById(reactionId).orElse(null);
        ManageStatusUtils.checkReadStatus(reaction, accountId);

        return reaction;
    }

    public Reaction deleteReaction(String accountId, String postId, String reactionId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Reaction reaction = reactionRepository.findById(reactionId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(reaction, accountId);

        Reaction updated = new Reaction(
                reaction.reactionId(),
                reaction.created(),
                reaction.createdBy(),
                reaction.emoji(),
                reaction.content(),
                reaction.postId(),
                reaction.isPublic(),
                reaction.isLocked(),
                reaction.isArchived(),
                reaction.isReviewing(),
                true
        );

        return reactionRepository.save(updated);
    }

    public List<Comment> listComment(String accountId, String postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Comment> comments = accountId == null
                ? commentRepository.findAll(false, false, false, postId, pageable)
                : commentRepository.findAllByCreatedBy(accountId, false, postId, pageable);

        return comments.getContent();
    }

    public Comment createComment(
            String accountId,
            String postId,
            List<String> contents
    ) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Comment comment = new Comment(
                RandomUtils.uuid(),
                TimeUtils.getMilliUnixTime(),
                accountId,
                contents,
                postId,
                true,
                false,
                false,
                true,
                false
        );

        return commentRepository.save(comment);
    }

    public Comment readComment(String accountId, String postId, String commentId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkReadStatus(post, accountId);

        Comment comment = commentRepository.findById(commentId).orElse(null);
        ManageStatusUtils.checkReadStatus(comment, accountId);

        return comment;
    }

    public Comment updateComment(String accountId, String postId, String commentId, List<String> contents) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Comment comment = commentRepository.findById(commentId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(comment, accountId);

        Comment updated = new Comment(
                comment.commentId(),
                comment.created(),
                comment.createdBy(),
                contents,
                comment.bindId(),
                comment.isPublic(),
                comment.isLocked(),
                comment.isArchived(),
                comment.isReviewing(),
                true
        );

        return commentRepository.save(updated);
    }

    public Comment deleteComment(String accountId, String postId, String commentId) {
        Post post = postRepository.findById(postId).orElse(null);
        ManageStatusUtils.checkUpdateStatus(post, accountId);

        Comment comment = commentRepository.findById(commentId).orElse(null);
        ManageStatusUtils.checkDeleteStatus(comment, accountId);

        Comment updated = new Comment(
                comment.commentId(),
                comment.created(),
                comment.createdBy(),
                comment.contents(),
                comment.bindId(),
                comment.isPublic(),
                comment.isLocked(),
                comment.isArchived(),
                comment.isReviewing(),
                true
        );

        return commentRepository.save(updated);
    }
}
