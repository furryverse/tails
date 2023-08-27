package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Asteroid;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AsteroidRepository extends MongoRepository<Asteroid, String> {
    <S extends Asteroid> @NotNull S save(@NotNull S asteroid);
}
