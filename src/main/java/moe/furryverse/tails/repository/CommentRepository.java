package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CommentRepository extends MongoRepository<Comment, String> {
    @NotNull <S extends Comment> S save(@NotNull S entity);

    @Query("{'is_locked': ?0,'is_reviewing': ?1, 'is_deleted': ?2, 'bind_id': ?3}")
    @NotNull Page<Comment> findAll(boolean isLocked, boolean isReviewing, boolean isDeleted, String bindId, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1, 'bind_id': ?2}")
    @NotNull Page<Comment> findAllByCreatedBy(@NotNull String createdBy, boolean isDeleted, String bindId, @NotNull Pageable pageable);
}
