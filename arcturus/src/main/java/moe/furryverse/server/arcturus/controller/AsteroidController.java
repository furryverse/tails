package moe.furryverse.server.arcturus.controller;

import moe.furryverse.server.arcturus.model.Asteroid;
import moe.furryverse.server.common.annotation.AccessCheck;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0")
public class AsteroidController {
    @GetMapping("/asteroid")
    @AccessCheck(access = {Access.CLUSTER_LIST})
    public Message<?> listAsteroid() {
        return null;
    }

    @GetMapping("/asteroid/{asteroidId}")
    @AccessCheck(access = {Access.ASTEROID_GET})
    public Message<?> getAsteroid(@PathVariable String asteroidId) {
        return null;
    }

    @PostMapping("/asteroid/{asteroidId}")
    @AccessCheck(access = {Access.ASTEROID_CREATE})
    public Message<?> createAsteroid(@PathVariable String asteroidId, @RequestBody Asteroid asteroid) {
        return null;
    }

    @PutMapping("/asteroid/{asteroidId}")
    @AccessCheck(access = {Access.ASTEROID_UPDATE})
    public Message<?> updateAsteroid(@PathVariable String asteroidId, @RequestBody Asteroid asteroid) {
        return null;
    }

    @DeleteMapping("/asteroid/{asteroidId}")
    @AccessCheck(access = {Access.ASTEROID_DELETE})
    public Message<?> deleteAsteroid(@PathVariable String asteroidId) {
        return null;
    }
}
