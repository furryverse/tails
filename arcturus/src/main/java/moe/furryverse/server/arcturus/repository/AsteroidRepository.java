package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Asteroid;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AsteroidRepository extends MongoRepository<Asteroid, String> {
    <S extends Asteroid> @NotNull S save(@NotNull S asteroid);

    Asteroid findByAsteroidId(String asteroidId);

    @Query(value = "{ 'asteroid_id' : ?0 }")
    Asteroid updateByAsteroidId(String asteroidId, Asteroid asteroid);

    Asteroid deleteByAsteroidId(String asteroidId);
}
