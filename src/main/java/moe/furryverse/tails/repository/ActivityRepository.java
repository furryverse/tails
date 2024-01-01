package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Activity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    @NotNull <S extends Activity> S save(@NotNull S entity);

    @Query("{'is_locked': ?0,'is_reviewing': ?1, 'is_deleted': ?2}")
    @NotNull Page<Activity> findAll(boolean isLocked, boolean isReviewing, boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'account_id': ?0, 'is_deleted': ?1}")
    @NotNull Page<Activity> findAllByAccountId(@NotNull String accountId, boolean isDeleted, @NotNull Pageable pageable);
}
