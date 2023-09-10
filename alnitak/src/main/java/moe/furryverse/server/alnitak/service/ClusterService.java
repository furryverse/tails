package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Cluster;
import moe.furryverse.server.alnitak.repository.ClusterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClusterService {
    final ClusterRepository clusterRepository;

    public List<Cluster> listCluster() {
        return null;
    }

    public Cluster getCluster(String clusterId) {
        return null;
    }

    public Cluster createCluster(String clusterId, Cluster cluster) {
        return null;
    }

    public Cluster updateCluster(String clusterId, Cluster cluster) {
        return null;
    }

    public Cluster deleteCluster(String clusterId) {
        return null;
    }
}
