package moe.furryverse.server.arcturus.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.server.arcturus.model.Comment;
import moe.furryverse.server.arcturus.repository.CommentRepository;
import moe.furryverse.server.common.exception.NotFoundDataException;
import moe.furryverse.server.common.utils.Random;
import moe.furryverse.server.common.utils.Time;
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

    public Comment getComment(String accountId, String commentId) {
        Comment comment = commentRepository.findByCommentId(commentId);
        if (comment == null)
            throw new NotFoundDataException("could not find comment", "/api/v0/comment/" + commentId, "GET", accountId);

        return comment;
    }

    public Comment createComment(String postId, String accountId, List<String> contents) {
        Comment comment = new Comment(
                Time.getMilliUnixTime(),
                Random.uuid(),
                postId,
                accountId,
                contents
        );

        return commentRepository.save(comment);
    }

    public Comment updateComment(String commentId, Comment comment) {
        Comment oldComment = commentRepository.findByCommentId(commentId);
        if (oldComment == null)
            throw new NotFoundDataException("could not find comment", "/api/v0/comment/" + commentId, "PUT", comment.accountId());

        List<String> diff = textService.unifiedDiff(oldComment.contents(), comment.contents());

        historyService.createHistory(comment.accountId(), commentId, diff);
        return commentRepository.updateByCommentId(commentId, comment);
    }

    public Comment deleteComment(String accountId, String commentId) {
        Comment comment = commentRepository.deleteByCommentId(commentId);
        if (comment == null)
            throw new NotFoundDataException("could not find comment", "/api/v0/comment/" + commentId, "DELETE", accountId);

        return comment;
    }
}
