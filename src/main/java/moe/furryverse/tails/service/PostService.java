package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.repository.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    final PostRepository postRepository;
    final CategoryRepository categoryRepository;
    final TagRepository tagRepository;
    final ThoughtRepository thoughtRepository;
    final ReactionRepository reactionRepository;
}
