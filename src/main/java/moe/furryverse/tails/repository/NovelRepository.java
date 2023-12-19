package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Novel;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NovelRepository extends MongoRepository<Novel, String> {
    @NotNull <S extends Novel> S save(@NotNull S novel);

    Novel findByNovelId(@NotNull String novelId);

    Novel deleteByNovelId(@NotNull String novelId);
}
