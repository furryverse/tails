package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

    @NotNull <S extends Post> S save(@NotNull S entity);

    Post findPostByPostId(String postId);

    Post deletePostByPostId(String postId);
}
