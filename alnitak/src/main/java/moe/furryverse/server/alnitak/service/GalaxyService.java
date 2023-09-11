package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Galaxy;
import moe.furryverse.server.alnitak.repository.GalaxyRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalaxyService {
    final GalaxyRepository galaxyRepository;

    public List<Galaxy> listGalaxy() {
        return null;
    }

    public Galaxy getGalaxy(String accountId, String galaxyId) {
        Galaxy galaxy = galaxyRepository.findByGalaxyId(galaxyId);
        if (galaxy == null)
            throw new NotFoundDataException("could not find galaxy", "/api/v0/galaxy/" + galaxyId, "GET", accountId);

        return galaxy;
    }

    public Galaxy createGalaxy(
            String accountId,
            String clusterId,
            String title,
            String background,
            List<String> nucleus,
            List<String> viewers,
            List<String> collaborators,
            boolean isPublic,
            boolean isLocked,
            boolean isReviewing,
            boolean isArchived
    ) {
        Galaxy galaxy = new Galaxy(
                Time.getMilliUnixTime(),
                Random.uuid(),
                clusterId,
                title,
                background,
                nucleus,
                viewers,
                collaborators,
                isPublic,
                isLocked,
                isReviewing,
                isArchived
        );

        return galaxyRepository.save(galaxy);
    }

    public Galaxy updateGalaxy(String accountId, String galaxyId, Galaxy galaxy) {
        Galaxy oldGalaxy = galaxyRepository.findByGalaxyId(galaxyId);
        if (oldGalaxy == null)
            throw new NotFoundDataException("could not find galaxy", "/api/v0/galaxy/" + galaxyId, "PUT", accountId);

        return galaxyRepository.save(galaxy);
    }

    public Galaxy deleteGalaxy(String accountId, String galaxyId) {
        Galaxy galaxy = galaxyRepository.deleteByGalaxyId(galaxyId);
        if (galaxy == null)
            throw new NotFoundDataException("could not find galaxy", "/api/v0/galaxy/" + galaxyId, "DELETE", accountId);

        return galaxy;
    }
}
