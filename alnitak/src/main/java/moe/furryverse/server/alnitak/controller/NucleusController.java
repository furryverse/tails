package moe.furryverse.server.alnitak.controller;

import moe.furryverse.server.alnitak.model.Nucleus;
import moe.furryverse.server.common.annotation.AccessCheck;
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
    public Message<?> getNucleus(@PathVariable String nucleusId) {
        return null;
    }

    @PostMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_CREATE})
    public Message<?> createNucleus(@PathVariable String nucleusId, @RequestBody Nucleus nucleus) {
        return null;
    }

    @PutMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_UPDATE})
    public Message<?> updateNucleus(@PathVariable String nucleusId, @RequestBody Nucleus nucleus) {
        return null;
    }

    @DeleteMapping("/nucleus/{nucleusId}")
    @AccessCheck(access = {Access.NUCLEUS_DELETE})
    public Message<?> deleteNucleus(@PathVariable String nucleusId) {
        return null;
    }
}
