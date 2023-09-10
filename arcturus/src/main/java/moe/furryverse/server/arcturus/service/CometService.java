package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Comet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CometService {
    final TextService textService;

    public List<Comet> listComet() {
        return null;
    }

    public Comet getComet(String cometId) {
        return null;
    }

    public Comet createComet(String cometId, Comet comet) {
        return null;
    }

    public Comet updateComet(String cometId, Comet comet) {
        return null;
    }

    public Comet deleteComet(String cometId) {
        return null;
    }
}
