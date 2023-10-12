package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.Comment;
import moe.furryverse.tails.repository.CommentRepository;
import moe.furryverse.tails.utils.Random;
import moe.furryverse.tails.utils.Time;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    final TextService textService;
    final HistoryService historyService;
    final CommentRepository commentRepository;

    public List<Comment> listComment(String postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment getComment(String accountId, String postId, String commentId) {
        Comment comment = commentRepository.findByPostIdAndCommentId(postId, commentId);
        if (comment == null)
            throw new NotFoundDataException("could not find comment", "/api/v0/comment/" + commentId, "GET", accountId);

        return comment;
    }

    public Comment createComment(String accountId, String postId, Comment comment) {
        Comment join = new Comment(
                Time.getMilliUnixTime(),
                Random.uuid(),
                postId,
                accountId,
                comment.contents()
        );

        return commentRepository.save(join);
    }

    public Comment updateComment(String postId, String commentId, Comment comment) {
        Comment oldComment = commentRepository.findByPostIdAndCommentId(postId, commentId);
        if (oldComment == null)
            throw new NotFoundDataException("could not find comment", "/api/v0/comment/" + commentId, "PUT", comment.accountId());

        List<String> diffs = textService.unifiedDiff(oldComment.contents(), comment.contents());

        historyService.createHistory(comment.accountId(), postId, commentId, diffs);
        return commentRepository.updateByPostIdAndCommentId(postId, commentId, comment);
    }

    public Comment deleteComment(String accountId, String postId, String commentId) {
        Comment comment = commentRepository.deleteByPostIdAndCommentId(postId, commentId);
        if (comment == null)
            throw new NotFoundDataException("could not find comment", "/api/v0/comment/" + commentId, "DELETE", accountId);

        return comment;
    }
}
