package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.History;
import moe.furryverse.tails.repository.HistoryRepository;
import moe.furryverse.tails.utils.Random;
import moe.furryverse.tails.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    final TextService textService;
    final HistoryRepository historyRepository;

    public List<History> listHistory(String postId, String bindId) {
        return historyRepository.findByPostIdAndBindId(postId, bindId);
    }

    public History getHistory(String accountId, String postId, String bindId, String historyId) {
        History history = historyRepository.findByPostIdAndBindIdAndHistoryId(postId, bindId, historyId);
        if (history == null)
            throw new NotFoundDataException("could not find history", "/api/v0/history/" + historyId, "GET", accountId);

        return history;
    }

    @SuppressWarnings("UnusedReturnValue")
    public History createHistory(String accountId, String postId, String bindId, List<String> diffs) {
        History history = new History(
                Time.getMilliUnixTime(),
                Random.uuid(),
                accountId,
                postId,
                bindId,
                diffs
        );

        return historyRepository.save(history);
    }


    public History deleteHistory(String accountId, String postId, String bindId, String historyId) {
        History history = historyRepository.deleteByPostIdAndBindIdAndHistoryId(postId, bindId, historyId);
        if (history == null)
            throw new NotFoundDataException("could not find history", "/api/v0/history/" + historyId, "DELETE", accountId);

        return history;
    }
}
