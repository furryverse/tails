package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Planet;
import moe.furryverse.server.arcturus.repository.PlanetRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanetService {
    final TextService textService;
    final StardustService stardustService;
    final PlanetRepository planetRepository;

    public List<Planet> listPlanet(String galaxyId) {
        return planetRepository.findByGalaxyId(galaxyId);
    }

    public Planet getPlanet(String accountId, String planetId) {
        Planet planet = planetRepository.findByPlanetId(planetId);
        if (planet == null)
            throw new NotFoundDataException("could not find planet", "/api/v0/planet/" + planetId, "GET", accountId);

        return planet;
    }

    public Planet createPlanet(String galaxyId, String accountId, List<String> contents) {
        Planet planet = new Planet(
                Time.getMilliUnixTime(),
                Random.uuid(),
                galaxyId,
                accountId,
                contents
        );

        return planetRepository.save(planet);
    }

    public Planet updatePlanet(String planetId, Planet planet) {
        Planet oldPlanet = planetRepository.findByPlanetId(planetId);
        if (oldPlanet == null)
            throw new NotFoundDataException("could not find planet", "/api/v0/planet/" + planetId, "PUT", planet.accountId());

        List<String> diff = textService.unifiedDiff(oldPlanet.contents(), planet.contents());

        stardustService.createStardust(planet.accountId(), planetId, diff);
        return planetRepository.updateByPlanetId(planetId, planet);
    }

    public Planet deletePlanet(String accountId, String planetId) {
        Planet planet = planetRepository.deleteByPlanetId(planetId);
        if (planet == null)
            throw new NotFoundDataException("could not find planet", "/api/v0/planet/" + planetId, "DELETE", accountId);

        return planet;
    }
}
