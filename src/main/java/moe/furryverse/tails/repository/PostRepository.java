package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {
    <S extends Post> @NotNull S save(@NotNull S post);

    Post findByPostId(String postId);

    @Query(value = "{ 'post_id' : ?0 }")
    Post updateByPostId(String postId, Post post);

    Post deleteByPostId(String postId);

    boolean existsByPostId(String postId);

    @Query(value = "{ 'is_public' : ?0, 'is_locked': ?1, 'is_reviewing': ?2, 'is_archived': ?3 }")
    Page<Post> findAll(boolean isPublic, boolean isLocked, boolean isReviewing, boolean isArchived, Pageable pageable);
}
