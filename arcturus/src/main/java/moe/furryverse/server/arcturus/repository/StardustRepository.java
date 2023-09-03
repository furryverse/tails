package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Stardust;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StardustRepository extends MongoRepository<Stardust, String> {
    <S extends Stardust> @NotNull S save(@NotNull S stardust);

    Stardust findByStardustId(String stardustId);

    @Query(value = "{ 'stardust_id' : ?0 }")
    Stardust updateByStardustId(String stardustId, Stardust stardust);

    Stardust deleteByStardustId(String stardustId);
}
