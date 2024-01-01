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
        Page<Post> posts = postRepository.findAllByCreatedBy(accountId, pageable);

        return posts.getContent();
    }

    public List<Thought> listThought(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Thought> thoughts = thoughtRepository.findAllByCreatedBy(accountId, pageable);

        return thoughts.getContent();
    }

    public List<Reaction> listReaction(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Reaction> reactions = reactionRepository.findAllByCreatedBy(accountId, pageable);

        return reactions.getContent();
    }

    public List<Comment> listComment(String accountId, int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Comment> comments = commentRepository.findAllByCreatedBy(accountId, pageable);

        return comments.getContent();
    }
}
