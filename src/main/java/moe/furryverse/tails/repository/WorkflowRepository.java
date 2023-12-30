package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Workflow;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface WorkflowRepository extends MongoRepository<Workflow, String> {
    @NotNull <S extends Workflow> S save(@NotNull S workflow);

    @NotNull Page<Workflow> findAll(@NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<Workflow> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
