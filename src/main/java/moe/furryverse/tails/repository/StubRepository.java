package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Stub;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StubRepository extends MongoRepository<Stub, String> {
    @NotNull <S extends Stub> S save(@NotNull S entity);

    Page<Stub> findAllByAccountIdAndActivityId(String accountId, String activityId, Pageable pageable);

    @Query("{'is_deleted': ?0}")
    @NotNull Page<Stub> findAll(boolean isDeleted, @NotNull Pageable pageable);

    Stub findStubByStubId(String stubId);
}
