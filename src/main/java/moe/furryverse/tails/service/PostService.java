package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.Comment;
import moe.furryverse.tails.model.Post;
import moe.furryverse.tails.model.Reaction;
import moe.furryverse.tails.model.Thought;
import moe.furryverse.tails.repository.CommentRepository;
import moe.furryverse.tails.repository.PostRepository;
import moe.furryverse.tails.repository.ReactionRepository;
import moe.furryverse.tails.repository.ThoughtRepository;
import moe.furryverse.tails.utils.ManageStatusUtils;
import moe.furryverse.tails.utils.RandomUtils;
import moe.furryverse.tails.utils.TimeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
