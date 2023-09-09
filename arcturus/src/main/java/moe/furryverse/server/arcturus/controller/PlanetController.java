package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Planet;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class PlanetController {
    @GetMapping("/planet")
    @AccessCheck(access = {Access.PLANET_LIST})
    public Message<?> listPlanet() {
        return null;
    }

    @GetMapping("/planet/{planetId}")
    @AccessCheck(access = {Access.PLANET_GET})
    public Message<?> getPlanet(@PathVariable String planetId) {
        return null;
    }

    @PostMapping("/planet/{planetId}")
    @AccessCheck(access = {Access.PLANET_CREATE})
    public Message<?> createPlanet(@PathVariable String planetId, @RequestBody Planet planet) {
        return null;
    }

    @PutMapping("/planet/{planetId}")
    @AccessCheck(access = {Access.PLANET_UPDATE})
    public Message<?> updatePlanet(@PathVariable String planetId, @RequestBody Planet planet) {
        return null;
    }

    @DeleteMapping("/planet/{planetId}")
    @AccessCheck(access = {Access.PLANET_DELETE})
    public Message<?> deletePlanet(@PathVariable String planetId) {
        return null;
    }
}
