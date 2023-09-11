package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.History;
import moe.furryverse.server.arcturus.repository.HistoryRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    final TextService textService;
    final HistoryRepository historyRepository;

    public List<History> listHistory(String bindId) {
        return historyRepository.findByBindId(bindId);
    }

    public History getHistory(String accountId, String historyId) {
        History history = historyRepository.findByHistoryId(historyId);
        if (history == null)
            throw new NotFoundDataException("could not find history", "/api/v0/history/" + historyId, "GET", accountId);

        return history;
    }

    @SuppressWarnings("UnusedReturnValue")
    public History createHistory(String accountId, String bindId, List<String> diffs) {
        History history = new History(
                Time.getMilliUnixTime(),
                Random.uuid(),
                accountId,
                bindId,
                diffs
        );

        return historyRepository.save(history);
    }


    public History deleteHistory(String accountId, String historyId) {
        History history = historyRepository.deleteByHistoryId(historyId);
        if (history == null)
            throw new NotFoundDataException("could not find history", "/api/v0/history/" + historyId, "DELETE", accountId);

        return history;
    }
}
