package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Stardust;
import moe.furryverse.server.arcturus.repository.StardustRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StardustService {
    final TextService textService;
    final StardustRepository stardustRepository;

    public List<Stardust> listStardust(String bindId) {
        return stardustRepository.findByBindId(bindId);
    }

    public Stardust getStardust(String accountId, String stardustId) {
        Stardust stardust = stardustRepository.findByStardustId(stardustId);
        if (stardust == null)
            throw new NotFoundDataException("could not find stardust", "/api/v0/stardust/" + stardustId, "GET", accountId);

        return stardust;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Stardust createStardust(String accountId, String bindId, List<String> diffs) {
        Stardust stardust = new Stardust(
                Time.getMilliUnixTime(),
                Random.uuid(),
                accountId,
                bindId,
                diffs
        );

        return stardustRepository.save(stardust);
    }


    public Stardust deleteStardust(String accountId, String stardustId) {
        Stardust stardust = stardustRepository.deleteByStardustId(stardustId);
        if (stardust == null)
            throw new NotFoundDataException("could not find stardust", "/api/v0/stardust/" + stardustId, "DELETE", accountId);

        return stardust;
    }
}
