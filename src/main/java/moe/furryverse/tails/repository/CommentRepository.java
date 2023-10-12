package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Comment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    <S extends Comment> @NotNull S save(@NotNull S comment);

    Comment findByPostIdAndCommentId(String postId, String commentId);

    List<Comment> findByPostId(String postId);

    @Query(value = "{ 'post_id' : ?0, 'comment_id' : ?1 }")
    Comment updateByPostIdAndCommentId(String postId, String commentId, Comment comment);

    Comment deleteByPostIdAndCommentId(String postId, String commentId);
}
