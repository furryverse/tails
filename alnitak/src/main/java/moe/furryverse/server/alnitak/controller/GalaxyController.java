package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Galaxy;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class GalaxyController {
    @GetMapping("/galaxy")
    @AccessCheck(access = {Access.GALAXY_LIST})
    public Message<?> listGalaxy() {
        return null;
    }

    @GetMapping("/galaxy/{galaxyId}")
    @AccessCheck(access = {Access.GALAXY_GET})
    public Message<?> getGalaxy(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String galaxyId
    ) {
        return null;
    }

    @PostMapping("/galaxy/{galaxyId}")
    @AccessCheck(access = {Access.GALAXY_CREATE})
    public Message<?> createGalaxy(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String galaxyId,
            @RequestBody Galaxy galaxy
    ) {
        return null;
    }

    @PutMapping("/galaxy/{galaxyId}")
    @AccessCheck(access = {Access.GALAXY_UPDATE})
    public Message<?> updateGalaxy(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String galaxyId,
            @RequestBody Galaxy galaxy
    ) {
        return null;
    }

    @DeleteMapping("/galaxy/{galaxyId}")
    @AccessCheck(access = {Access.GALAXY_DELETE})
    public Message<?> deleteGalaxy(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String galaxyId
    ) {
        return null;
    }
}
