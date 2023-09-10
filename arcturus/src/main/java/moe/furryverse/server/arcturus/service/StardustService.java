package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Stardust;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StardustService {
    final TextService textService;

    public List<Stardust> listStardust() {
        return null;
    }

    public Stardust getStardust(String stardustId) {
        return null;
    }

    public Stardust createStardust(String stardustId, Stardust stardust) {
        return null;
    }

    public Stardust updateStardust(String stardustId, Stardust stardust) {
        return null;
    }

    public Stardust deleteStardust(String stardustId) {
        return null;
    }
}
