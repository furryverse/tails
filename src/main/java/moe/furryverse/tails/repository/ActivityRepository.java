package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Activity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    @NotNull <S extends Activity> S save(@NotNull S entity);

    @Query("{'is_deleted': ?0}")
    @NotNull Page<Activity> findAll(boolean isDeleted, @NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<Activity> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
