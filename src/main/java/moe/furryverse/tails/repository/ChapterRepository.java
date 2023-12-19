package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Chapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
    @NotNull <S extends Chapter> S save(@NotNull S chapter);

    Chapter findByChapterId(@NotNull String chapterId);

    Chapter deleteByChapterId(@NotNull String chapterId);
}
