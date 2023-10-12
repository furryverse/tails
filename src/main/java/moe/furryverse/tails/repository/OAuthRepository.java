package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.OAuth;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OAuthRepository extends MongoRepository<OAuth, String> {
    <S extends OAuth> @NotNull S save(@NotNull S oauth);

    // 根据 uid 查找和删除
    OAuth findByAccountId(@NotNull String accountId);

    OAuth deleteByAccountId(@NotNull String accountId);

    // 根据 openid 查找和删除
    OAuth findByOpenid(@NotNull String openid);

    OAuth deleteByOpenid(@NotNull String openid);
}