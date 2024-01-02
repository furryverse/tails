package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Stub;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StubRepository extends MongoRepository<Stub, String> {
    @NotNull <S extends Stub> S save(@NotNull S entity);

    Page<Stub> findAllByCreatedByAndActivityId(String createdBy, String activityId, Pageable pageable);

    int countAllByCreatedByAndActivityId(String createdBy, String activityId);

    @NotNull Page<Stub> findAll(@NotNull Pageable pageable);

    Stub findStubByCreatedByAndActivityIdAndStubId(String createdBy, String activityId, String stubId);
}
