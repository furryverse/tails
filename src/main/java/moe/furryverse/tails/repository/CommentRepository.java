package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
    @NotNull <S extends Comment> S save(@NotNull S entity);

    Comment findCommentByCommentId(String commentId);

    Comment deleteCommentByCommentId(String commentId);
}
