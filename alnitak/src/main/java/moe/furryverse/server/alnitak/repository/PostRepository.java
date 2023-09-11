package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {
    <S extends Post> @NotNull S save(@NotNull S post);

    Post findByPostId(String postId);

    @Query(value = "{ 'post_id' : ?0 }")
    Post updateByPostId(String postId, Post post);

    Post deleteByPostId(String postId);
}
