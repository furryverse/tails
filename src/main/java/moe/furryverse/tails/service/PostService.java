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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Thought> listThought(String accountId, String postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Thought> thoughts = accountId == null
                ? thoughtRepository.findAll(false, false, false, postId, pageable)
                : thoughtRepository.findAllByCreatedBy(accountId, false, postId, pageable);

        return thoughts.getContent();
    }

    public List<Reaction> listReaction(String accountId, String postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Reaction> reactions = accountId == null
                ? reactionRepository.findAll(false, false, false, postId, pageable)
                : reactionRepository.findAllByCreatedBy(accountId, false, postId, pageable);

        return reactions.getContent();
    }

    public List<Comment> listComment(String accountId, String postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Comment> comments = accountId == null
                ? commentRepository.findAll(false, false, false, postId, pageable)
                : commentRepository.findAllByCreatedBy(accountId, false, postId, pageable);

        return comments.getContent();
    }
}
