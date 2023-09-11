package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Nucleus;
import moe.furryverse.server.alnitak.repository.NucleusRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NucleusService {
    final NucleusRepository nucleusRepository;

    public List<Nucleus> listNucleus() {
        return null;
    }

    public Nucleus getNucleus(String accountId, String nucleusId) {
        Nucleus nucleus = nucleusRepository.findByNucleusId(nucleusId);
        if (nucleus == null)
            throw new NotFoundDataException("could not find nucleus", "/api/v0/nucleus/" + nucleusId, "GET", accountId);

        return nucleus;
    }

    public Nucleus createNucleus(
            String accountId,
            String name,
            String color
    ) {
        Nucleus nucleus = new Nucleus(
                Time.getMilliUnixTime(),
                Random.uuid(),
                name,
                color,
                accountId
        );

        return nucleusRepository.save(nucleus);
    }

    public Nucleus updateNucleus(String accountId, String nucleusId, Nucleus nucleus) {
        Nucleus nucleus1 = nucleusRepository.findByNucleusId(nucleusId);
        if (nucleus1 == null)
            throw new NotFoundDataException("could not find nucleus", "/api/v0/nucleus/" + nucleusId, "PUT", accountId);

        return nucleusRepository.save(nucleus);
    }

    public Nucleus deleteNucleus(String accountId, String nucleusId) {
        Nucleus nucleus = nucleusRepository.findByNucleusId(nucleusId);
        if (nucleus == null)
            throw new NotFoundDataException("could not find nucleus", "/api/v0/nucleus/" + nucleusId, "DELETE", accountId);

        return nucleus;
    }
}
