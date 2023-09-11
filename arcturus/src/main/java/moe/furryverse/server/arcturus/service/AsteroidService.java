package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Asteroid;
import moe.furryverse.server.arcturus.repository.AsteroidRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsteroidService {
    final TextService textService;
    final StardustService stardustService;
    final AsteroidRepository asteroidRepository;

    public List<Asteroid> listAsteroid(String galaxyId) {
        return asteroidRepository.findByGalaxyId(galaxyId);
    }

    public Asteroid getAsteroid(String accountId, String asteroidId) {
        Asteroid asteroid = asteroidRepository.findByAsteroidId(asteroidId);
        if (asteroid == null)
            throw new NotFoundDataException("could not find asteroid", "/api/v0/asteroid/" + asteroidId, "GET", accountId);

        return asteroid;
    }

    public Asteroid createAsteroid(String galaxyId, String accountId, List<String> contents) {
        Asteroid asteroid = new Asteroid(
                Time.getMilliUnixTime(),
                Random.uuid(),
                galaxyId,
                accountId,
                contents
        );

        return asteroidRepository.save(asteroid);
    }

    public Asteroid updateAsteroid(String asteroidId, Asteroid asteroid) {
        Asteroid oldAsteroid = asteroidRepository.findByAsteroidId(asteroidId);
        if (oldAsteroid == null)
            throw new NotFoundDataException("could not find asteroid", "/api/v0/asteroid/" + asteroidId, "PUT", asteroid.accountId());

        List<String> diff = textService.unifiedDiff(oldAsteroid.contents(), asteroid.contents());

        stardustService.createStardust(asteroid.accountId(), asteroidId, diff);
        return asteroidRepository.updateByAsteroidId(asteroidId, asteroid);
    }

    public Asteroid deleteAsteroid(String accountId, String asteroidId) {
        Asteroid asteroid = asteroidRepository.deleteByAsteroidId(asteroidId);
        if (asteroid == null)
            throw new NotFoundDataException("could not find asteroid", "/api/v0/asteroid/" + asteroidId, "DELETE", accountId);

        return asteroid;
    }
}
