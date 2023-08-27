package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Planet;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {
    <S extends Planet> @NotNull S save(@NotNull S planet);
}
