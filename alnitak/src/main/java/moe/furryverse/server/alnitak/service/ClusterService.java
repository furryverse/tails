package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Cluster;
import moe.furryverse.server.alnitak.repository.ClusterRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClusterService {
    final ClusterRepository clusterRepository;

    public List<Cluster> listCluster() {
        return clusterRepository.findAll();
    }

    public Cluster getCluster(String accountId, String clusterId) {
        Cluster cluster = clusterRepository.findByClusterId(clusterId);
        if (cluster == null)
            throw new NotFoundDataException("could not find cluster", "/api/v0/cluster/" + clusterId, "GET", accountId);

        return cluster;
    }

    public Cluster createCluster(
            String accountId,
            String clusterId,
            String clusterName,
            String icon,
            String banner,
            String bannerBackground,
            String background,
            String description,
            List<String> reviewers,
            List<String> administrators,
            List<String> editors,
            List<String> viewers,
            boolean isPublic,
            boolean isLocked
    ) {
        Cluster cluster = new Cluster(
                Time.getMilliUnixTime(),
                Random.uuid(),
                clusterId,
                clusterName,
                icon,
                banner,
                bannerBackground,
                background,
                description,
                reviewers,
                administrators,
                editors,
                viewers,
                isPublic,
                isLocked
        );

        return clusterRepository.save(cluster);
    }

    public Cluster updateCluster(String accountId, String clusterId, Cluster cluster) {
        Cluster old = clusterRepository.findByClusterId(clusterId);
        if (old == null)
            throw new NotFoundDataException("could not find cluster", "/api/v0/cluster/" + clusterId, "PUT", accountId);

        return clusterRepository.updateByClusterId(clusterId, cluster);
    }

    public Cluster deleteCluster(String accountId, String clusterId) {
        Cluster cluster = clusterRepository.deleteByClusterId(clusterId);
        if (cluster == null)
            throw new NotFoundDataException("could not find cluster", "/api/v0/cluster/" + clusterId, "DELETE", accountId);

        return cluster;
    }
}
