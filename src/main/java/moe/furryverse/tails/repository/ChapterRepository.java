package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Chapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChapterRepository extends MongoRepository<Chapter, String> {
    @NotNull <S extends Chapter> S save(@NotNull S entity);

    @Query("{'is_deleted': ?0}")
    @NotNull Page<Chapter> findAll(boolean isDeleted, @NotNull Pageable pageable);

    @NotNull List<Chapter> findAllByNovelId(String novelId);

    @Query("{'created_by': ?0}")
    @NotNull Page<Chapter> findAllByCreatedBy(@NotNull String createdBy, @NotNull Pageable pageable);
}
