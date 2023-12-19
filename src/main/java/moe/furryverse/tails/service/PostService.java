package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Post;
import moe.furryverse.tails.repository.PostRepository;
import moe.furryverse.tails.utils.Random;
import moe.furryverse.tails.utils.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    final PostRepository postRepository;

    public List<Post> listPost(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size == 0 ? 20 : Math.max(size, 20),
                Sort.by(Sort.Order.desc("created"))
        );
        Page<Post> pages = postRepository.findAll(pageable);

        return pages.getContent();
    }

    public Post getPost(String accountId, String postId) {
        Post post = postRepository.findByPostId(postId);
        if (post == null)
            throw new NotFoundDataException("could not find post", "/api/v0/post/" + postId, "GET", accountId);

        return post;
    }

    public Post createPost(
            String accountId,
            Post post
    ) {
        Post join = new Post(
                Time.getMilliUnixTime(),
                Random.uuid(),
                post.categoryId(),
                post.title(),
                post.background(),
                post.tags(),
                post.viewers(),
                post.collaborators(),
                post.isPublic(),
                post.isLocked(),
                post.isReviewing(),
                post.isArchived()
        );

        return postRepository.save(join);
    }

    public Post updatePost(String accountId, String postId, Post post) {
        Post oldPost = postRepository.findByPostId(postId);
        if (oldPost == null)
            throw new NotFoundDataException("could not find post", "/api/v0/post/" + postId, "PUT", accountId);

        return postRepository.save(post);
    }

    public Post deletePost(String accountId, String postId) {
        Post post = postRepository.deleteByPostId(postId);
        if (post == null)
            throw new NotFoundDataException("could not find post", "/api/v0/post/" + postId, "DELETE", accountId);

        return post;
    }

    public boolean existsByPostId(String postId) {
        return postRepository.existsByPostId(postId);
    }
}
