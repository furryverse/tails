package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Cluster;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ClusterRepository extends MongoRepository<Cluster, String> {
    <S extends Cluster> @NotNull S save(@NotNull S cluster);

    @NotNull List<Cluster> findAll();

    Cluster findByClusterId(String clusterId);

    @Query(value = "{ 'cluster_id' : ?0 }")
    Cluster updateByClusterId(String clusterId, Cluster cluster);

    Cluster deleteByClusterId(String clusterId);
}
