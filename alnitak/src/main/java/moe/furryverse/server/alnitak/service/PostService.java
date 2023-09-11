package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Post;
import moe.furryverse.server.alnitak.repository.PostRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    final PostRepository postRepository;

    public List<Post> listPost() {
        return null;
    }

    public Post getPost(String accountId, String postId) {
        Post post = postRepository.findByPostId(postId);
        if (post == null)
            throw new NotFoundDataException("could not find post", "/api/v0/post/" + postId, "GET", accountId);

        return post;
    }

    public Post createPost(
            String accountId,
            String categoryId,
            String title,
            String background,
            List<String> tag,
            List<String> viewers,
            List<String> collaborators,
            boolean isPublic,
            boolean isLocked,
            boolean isReviewing,
            boolean isArchived
    ) {
        Post post = new Post(
                Time.getMilliUnixTime(),
                Random.uuid(),
                categoryId,
                title,
                background,
                tag,
                viewers,
                collaborators,
                isPublic,
                isLocked,
                isReviewing,
                isArchived
        );

        return postRepository.save(post);
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
}
