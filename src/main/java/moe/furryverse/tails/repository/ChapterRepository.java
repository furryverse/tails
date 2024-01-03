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

    @Query("{'is_locked': ?0,'is_reviewing': ?1, 'is_deleted': ?2, 'is_draft': ?3, 'is_public': ?4, 'novel_id':  ?5}")
    @NotNull Page<Chapter> findAll(boolean isLocked, boolean isReviewing, boolean isDeleted, boolean isDraft, boolean isPublic, String novelId, @NotNull Pageable pageable);

    @Query("{'created_by': ?0, 'is_deleted': ?1, 'novel_id':  ?2}")
    @NotNull Page<Chapter> findAll(@NotNull String createdBy, boolean isDeleted, String novelId, @NotNull Pageable pageable);

    @NotNull List<Chapter> findAllByNovelId(String novelId);
}
