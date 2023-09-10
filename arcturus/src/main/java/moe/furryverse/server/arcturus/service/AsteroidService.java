package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Asteroid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsteroidService {
    final TextService textService;

    public List<Asteroid> listAsteroid() {
        return null;
    }

    public Asteroid getAsteroid(String asteroidId) {
        return null;
    }

    public Asteroid createAsteroid(String asteroidId, Asteroid asteroid) {
        return null;
    }

    public Asteroid updateAsteroid(String asteroidId, Asteroid asteroid) {
        return null;
    }

    public Asteroid deleteAsteroid(String asteroidId) {
        return null;
    }
}
