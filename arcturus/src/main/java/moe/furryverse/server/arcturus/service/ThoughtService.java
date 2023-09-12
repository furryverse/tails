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

    public Thought getThought(String accountId, String postId, String thoughtId) {
        Thought thought = thoughtRepository.findByPostIdAndThoughtId(postId, thoughtId);
        if (thought == null)
            throw new NotFoundDataException("could not find thought", "/api/v0/thought/" + thoughtId, "GET", accountId);

        return thought;
    }

    public Thought createThought(String accountId, String postId, Thought thought) {
        Thought join = new Thought(
                Time.getMilliUnixTime(),
                Random.uuid(),
                postId,
                accountId,
                thought.contents()
        );

        return thoughtRepository.save(join);
    }

    public Thought updateThought(String accountId, String postId, String thoughtId, Thought thought) {
        Thought oldThought = thoughtRepository.findByPostIdAndThoughtId(postId, thoughtId);
        if (oldThought == null)
            throw new NotFoundDataException("could not find thought", "/api/v0/thought/" + thoughtId, "PUT", accountId);

        List<String> diffs = textService.unifiedDiff(oldThought.contents(), thought.contents());

        historyService.createHistory(thought.accountId(), postId, thoughtId, diffs);
        return thoughtRepository.updateByPostIdAndThoughtId(postId, thoughtId, thought);
    }

    public Thought deleteThought(String accountId, String postId, String thoughtId) {
        Thought thought = thoughtRepository.deleteByPostIdAndThoughtId(postId, thoughtId);
        if (thought == null)
            throw new NotFoundDataException("could not find thought", "/api/v0/thought/" + thoughtId, "DELETE", accountId);

        return thought;
    }
}
