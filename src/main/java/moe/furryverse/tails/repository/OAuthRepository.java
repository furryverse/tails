package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.OAuth;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OAuthRepository extends MongoRepository<OAuth, String> {
    @NotNull <S extends OAuth> S save(@NotNull S entity);

    @NotNull List<OAuth> findAll();
}
