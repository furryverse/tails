package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.config.PageConfiguration;
import moe.furryverse.tails.model.*;
import moe.furryverse.tails.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    final TagRepository tagRepository;
    final PostRepository postRepository;
    final ThoughtRepository thoughtRepository;
    final CommentRepository commentRepository;
    final ReactionRepository reactionRepository;
    final CategoryRepository categoryRepository;

    public List<Tag> listTag(int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Tag> tags = tagRepository.findAll(pageable);

        return tags.getContent();
    }

    public List<Post> listPost(int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.getContent();
    }

    public List<Category> listCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Category> categories = categoryRepository.findAll(pageable);

        return categories.getContent();
    }

    public List<Thought> listThought(int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Thought> thoughts = thoughtRepository.findAll(pageable);

        return thoughts.getContent();
    }

    public List<Reaction> listReaction(int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Reaction> reactions = reactionRepository.findAll(pageable);

        return reactions.getContent();
    }

    public List<Comment> listComment(int page, int size) {
        Pageable pageable = PageRequest.of(page, Math.min(size, PageConfiguration.DEFAULT_PAGE_SIZE));
        Page<Comment> comments = commentRepository.findAll(pageable);

        return comments.getContent();
    }
}
