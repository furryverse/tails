package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostRepository extends MongoRepository<Post, String> {

    @NotNull <S extends Post> S save(@NotNull S entity);

    @Query("{'account_id': ?0}")
    @NotNull Page<Post> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
