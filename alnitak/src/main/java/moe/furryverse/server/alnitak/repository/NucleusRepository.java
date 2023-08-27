package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Nucleus;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NucleusRepository extends MongoRepository<Nucleus, String> {
    <S extends Nucleus> @NotNull S save(@NotNull S nucleus);
}
