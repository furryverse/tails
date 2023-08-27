package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Cluster;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClusterRepository extends MongoRepository<Cluster, String> {
    <S extends Cluster> @NotNull S save(@NotNull S cluster);
}
