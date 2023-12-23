package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.OAuth;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OAuthRepository extends MongoRepository<OAuth, String> {
    @NotNull <S extends OAuth> S save(@NotNull S entity);

    OAuth findOAuthByAccountId(String oAuthId);

    OAuth deleteOAuthByAccountId(String oAuthId);
}
