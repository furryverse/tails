package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    <S extends Comment> @NotNull S save(@NotNull S comment);

    Comment findByPostIdAndCommentId(String postId, String commentId);

    List<Comment> findByPostId(String postId);

    Comment deleteByPostIdAndCommentId(String postId, String commentId);
}
