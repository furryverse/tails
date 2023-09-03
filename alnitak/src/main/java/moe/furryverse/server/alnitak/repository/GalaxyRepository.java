package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Galaxy;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GalaxyRepository extends MongoRepository<Galaxy, String> {
    <S extends Galaxy> @NotNull S save(@NotNull S galaxy);

    Galaxy findByGalaxyId(String galaxyId);

    @Query(value = "{ 'galaxy_id' : ?0 }")
    Galaxy updateByGalaxyId(String galaxyId, Galaxy galaxy);

    Galaxy deleteByGalaxyId(String galaxyId);
}
