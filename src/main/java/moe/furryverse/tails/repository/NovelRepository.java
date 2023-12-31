package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Novel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NovelRepository extends MongoRepository<Novel, String> {
    @NotNull <S extends Novel> S save(@NotNull S entity);

    @Query("{'is_public': ?0, 'is_locked': ?1, 'is_reviewing': ?2, 'is_deleted': ?3}")
    @NotNull Page<Novel> findAll(boolean isPublic, boolean isLocked, boolean isReviewing, boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'account_id': ?0, 'is_deleted':  ?1}")
    @NotNull Page<Novel> findAllByAccountId(@NotNull String accountId, boolean isDeleted, @NotNull Pageable pageable);
}
