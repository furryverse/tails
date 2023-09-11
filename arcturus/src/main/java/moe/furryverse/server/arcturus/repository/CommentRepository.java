package moe.furryverse.server.arcturus.repository;

import moe.furryverse.server.arcturus.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    <S extends Comment> @NotNull S save(@NotNull S comment);

    Comment findByCommentId(String commentId);

    List<Comment> findByPostId(String postId);

    @Query(value = "{ 'comment_id' : ?0 }")
    Comment updateByCommentId(String commentId, Comment comment);

    Comment deleteByCommentId(String commentId);
}
