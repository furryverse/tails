package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Galaxy;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GalaxyRepository extends MongoRepository<Galaxy, String> {
    <S extends Galaxy> @NotNull S save(@NotNull S galaxy);
}
