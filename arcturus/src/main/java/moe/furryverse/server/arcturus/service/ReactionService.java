package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Reaction;
import moe.furryverse.server.arcturus.repository.ReactionRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactionService {
    final TextService textService;
    final HistoryService historyService;
    final ReactionRepository reactionRepository;

    public List<Reaction> listReaction(String postId) {
        return reactionRepository.findByPostId(postId);
    }

    public Reaction getReaction(String accountId, String postId, String reactionId) {
        Reaction reaction = reactionRepository.findByPostIdAndReactionId(postId, reactionId);
        if (reaction == null)
            throw new NotFoundDataException("could not find reaction", "/api/v0/reaction/" + reactionId, "GET", accountId);

        return reaction;
    }

    public Reaction createReaction(String accountId, String postId, Reaction reaction) {
        Reaction join = new Reaction(
                Time.getMilliUnixTime(),
                Random.uuid(),
                postId,
                accountId,
                reaction.emoji(),
                reaction.content()
        );

        return reactionRepository.save(join);
    }

    public Reaction deleteReaction(String accountId, String postId, String reactionId) {
        Reaction reaction = reactionRepository.deleteByPostIdAndReactionId(postId, reactionId);
        if (reaction == null)
            throw new NotFoundDataException("could not find reaction", "/api/v0/reaction/" + reactionId, "DELETE", accountId);

        return reaction;
    }
}
