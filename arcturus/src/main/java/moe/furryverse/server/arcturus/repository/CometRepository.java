package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Comet;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CometRepository extends MongoRepository<Comet, String> {
    <S extends Comet> @NotNull S save(@NotNull S comet);

    Comet findByCometId(String cometId);

    @Query(value = "{ 'comet_id' : ?0 }")
    Comet updateByCometId(String cometId, Comet comet);

    Comet deleteByCometId(String cometId);
}
