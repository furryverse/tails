package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Comet;
import moe.furryverse.server.arcturus.repository.CometRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CometService {
    final TextService textService;
    final StardustService stardustService;
    final CometRepository cometRepository;

    public List<Comet> listComet(String galaxyId) {
        return cometRepository.findByGalaxyId(galaxyId);
    }

    public Comet getComet(String accountId, String cometId) {
        Comet comet = cometRepository.findByCometId(cometId);
        if (comet == null)
            throw new NotFoundDataException("could not find comet", "/api/v0/comet/" + cometId, "GET", accountId);

        return comet;
    }

    public Comet createComet(String galaxyId, String accountId, String emoji, String content) {
        Comet comet = new Comet(
                Time.getMilliUnixTime(),
                Random.uuid(),
                galaxyId,
                accountId,
                emoji,
                content
        );

        return cometRepository.save(comet);
    }

    public Comet deleteComet(String accountId, String cometId) {
        Comet comet = cometRepository.deleteByCometId(cometId);
        if (comet == null)
            throw new NotFoundDataException("could not find comet", "/api/v0/comet/" + cometId, "DELETE", accountId);

        return comet;
    }
}
