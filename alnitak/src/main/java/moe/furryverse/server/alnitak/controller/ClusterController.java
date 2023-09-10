package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Cluster;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class ClusterController {
    @GetMapping("/cluster")
    @AccessCheck(access = {Access.CLUSTER_LIST})
    public Message<?> listCluster() {
        return null;
    }

    @GetMapping("/cluster/{clusterId}")
    @AccessCheck(access = {Access.CLUSTER_GET})
    public Message<?> getCluster(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String clusterId
    ) {
        return null;
    }

    @PostMapping("/cluster/{clusterId}")
    @AccessCheck(access = {Access.CLUSTER_CREATE})
    public Message<?> createCluster(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String clusterId,
            @RequestBody Cluster cluster
    ) {
        return null;
    }

    @PutMapping("/cluster/{clusterId}")
    @AccessCheck(access = {Access.CLUSTER_UPDATE})
    public Message<?> updateCluster(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String clusterId,
            @RequestBody Cluster cluster
    ) {
        return null;
    }

    @DeleteMapping("/cluster/{clusterId}")
    @AccessCheck(access = {Access.CLUSTER_DELETE})
    public Message<?> deleteCluster(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String clusterId,
            @RequestBody Cluster cluster
    ) {
        return null;
    }
}