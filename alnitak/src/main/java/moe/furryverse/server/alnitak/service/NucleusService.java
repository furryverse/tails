package moe.furryverse.server.alnitak.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.alnitak.model.Nucleus;
import moe.furryverse.server.alnitak.repository.NucleusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NucleusService {
    final NucleusRepository nucleusRepository;

    public List<Nucleus> listNucleus() {
        return null;
    }

    public Nucleus getNucleus(String nucleusId) {
        return null;
    }

    public Nucleus createNucleus(String nucleusId, Nucleus nucleus) {
        return null;
    }

    public Nucleus updateNucleus(String nucleusId, Nucleus nucleus) {
        return null;
    }

    public Nucleus deleteNucleus(String nucleusId) {
        return null;
    }
}
