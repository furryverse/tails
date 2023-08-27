package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Stardust;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StardustRepository extends MongoRepository<Stardust, String> {
    <S extends Stardust> @NotNull S save(@NotNull S stardust);
}
