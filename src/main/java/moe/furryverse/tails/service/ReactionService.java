package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Reaction;
import moe.furryverse.tails.repository.ReactionRepository;
import moe.furryverse.tails.utils.Random;
import moe.furryverse.tails.utils.Time;
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
