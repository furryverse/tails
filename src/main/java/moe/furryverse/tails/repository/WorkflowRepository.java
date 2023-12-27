package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Workflow;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkflowRepository extends MongoRepository<Workflow, String> {
    @NotNull <S extends Workflow> S save(@NotNull S workflow);
}
