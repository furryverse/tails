package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Comet;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CometRepository extends MongoRepository<Comet, String> {
    <S extends Comet> @NotNull S save(@NotNull S comet);
}
