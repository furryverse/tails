package moe.furryverse.server.ascella.repository;

import moe.furryverse.server.common.model.OAuth;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OAuthRepository extends MongoRepository<OAuth, String> {
    <S extends OAuth> @NotNull S save(@NotNull S entity);

    // 根据 uid 查找和删除
    OAuth findByUid(@NotNull String uid);

    OAuth deleteByUid(@NotNull String uid);

    // 根据 openid 查找和删除
    OAuth findByOpenid(@NotNull String openid);

    OAuth deleteByOpenid(@NotNull String openid);
}
