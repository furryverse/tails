package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Nucleus;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class NucleusController {
    @GetMapping("/nucleus")
    @AccessCheck(access = {Access.NUCLEUS_LIST})
    public Message<?> listNucleus() {
        return null;
    }

    @GetMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_GET})
    public Message<?> getNucleus(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String nucleusId
    ) {
        return null;
    }

    @PostMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_CREATE})
    public Message<?> createNucleus(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String nucleusId,
            @RequestBody Nucleus nucleus
    ) {
        return null;
    }

    @PutMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_UPDATE})
    public Message<?> updateNucleus(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String nucleusId,
            @RequestBody Nucleus nucleus
    ) {
        return null;
    }

    @DeleteMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_DELETE})
    public Message<?> deleteNucleus(
            @RequestHeader(value = Resource.CustomHeader.ACCOUNT_ID_HEADER) String accountId,
            @PathVariable String nucleusId
    ) {
        return null;
    }
}
