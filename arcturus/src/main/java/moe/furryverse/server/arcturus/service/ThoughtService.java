package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Thought;
import moe.furryverse.server.arcturus.repository.ThoughtRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThoughtService {
    final TextService textService;
    final HistoryService historyService;
    final ThoughtRepository thoughtRepository;

    public List<Thought> listThought(String postId) {
        return thoughtRepository.findByPostId(postId);
    }

    public Thought getThought(String accountId, String thoughtId) {
        Thought thought = thoughtRepository.findByThoughtId(thoughtId);
        if (thought == null)
            throw new NotFoundDataException("could not find thought", "/api/v0/thought/" + thoughtId, "GET", accountId);

        return thought;
    }

    public Thought createThought(String postId, String accountId, List<String> contents) {
        Thought thought = new Thought(
                Time.getMilliUnixTime(),
                Random.uuid(),
                postId,
                accountId,
                contents
        );

        return thoughtRepository.save(thought);
    }

    public Thought updateThought(String thoughtId, Thought thought) {
        Thought oldThought = thoughtRepository.findByThoughtId(thoughtId);
        if (oldThought == null)
            throw new NotFoundDataException("could not find thought", "/api/v0/thought/" + thoughtId, "PUT", thought.accountId());

        List<String> diff = textService.unifiedDiff(oldThought.contents(), thought.contents());

        historyService.createHistory(thought.accountId(), thoughtId, diff);
        return thoughtRepository.updateByThoughtId(thoughtId, thought);
    }

    public Thought deleteThought(String accountId, String thoughtId) {
        Thought thought = thoughtRepository.deleteByThoughtId(thoughtId);
        if (thought == null)
            throw new NotFoundDataException("could not find thought", "/api/v0/thought/" + thoughtId, "DELETE", accountId);

        return thought;
    }
}
