package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Planet;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlanetRepository extends MongoRepository<Planet, String> {
    <S extends Planet> @NotNull S save(@NotNull S planet);

    Planet findByPlanetId(String planetId);

    @Query(value = "{ 'planet_id' : ?0 }")
    Planet updateByPlanetId(String planetId, Planet planet);

    Planet deleteByPlanetId(String planetId);
}
