package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Commission;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommissionRepository extends MongoRepository<Commission, String> {
    @NotNull <S extends Commission> S save(@NotNull S commission);
}
