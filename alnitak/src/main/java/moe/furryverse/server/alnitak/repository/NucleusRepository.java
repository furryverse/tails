package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Nucleus;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NucleusRepository extends MongoRepository<Nucleus, String> {
    <S extends Nucleus> @NotNull S save(@NotNull S nucleus);

    Nucleus findByNucleusId(String nucleusId);

    @Query(value = "{ 'nucleus_id' : ?0 }")
    Nucleus updateByNucleusId(String nucleusId, Nucleus nucleus);

    Nucleus deleteByNucleusId(String nucleusId);
}
