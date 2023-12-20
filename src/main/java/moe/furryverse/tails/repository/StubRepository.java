package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Stub;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StubRepository extends MongoRepository<Stub, String> {
    @NotNull <S extends Stub> S save(@NotNull S s);

    Stub findByStubId(@NotNull String stubId);
}
