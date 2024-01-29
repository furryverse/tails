package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {

    @NotNull <S extends Post> S save(@NotNull S entity);

    @Query("{'is_locked': ?0,'is_reviewing': ?1, 'is_deleted': ?2}")
    @NotNull Page<Post> findAll(boolean isLocked, boolean isReviewing, boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1}")
    @NotNull Page<Post> findAllByCreatedBy(@NotNull String createdBy, boolean isDeleted, @NotNull Pageable pageable);
}
