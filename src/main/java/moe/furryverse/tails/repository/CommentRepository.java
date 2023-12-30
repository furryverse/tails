package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CommentRepository extends MongoRepository<Comment, String> {
    @NotNull <S extends Comment> S save(@NotNull S entity);

    @Query("{'is_deleted': ?0}")
    @NotNull Page<Comment> findAll(boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<Comment> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
