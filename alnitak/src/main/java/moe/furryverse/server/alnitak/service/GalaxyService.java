package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Galaxy;
import moe.furryverse.server.alnitak.repository.GalaxyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalaxyService {
    final GalaxyRepository galaxyRepository;

    public List<Galaxy> listGalaxy() {
        return null;
    }

    public Galaxy getGalaxy(String galaxyId) {
        return null;
    }

    public Galaxy createGalaxy(String galaxyId, Galaxy galaxy) {
        return null;
    }

    public Galaxy updateGalaxy(String galaxyId, Galaxy galaxy) {
        return null;
    }

    public Galaxy deleteGalaxy(String galaxyId) {
        return null;
    }
}
