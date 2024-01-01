package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TagRepository extends MongoRepository<Tag, String> {
    @NotNull <S extends Tag> S save(@NotNull S entity);

    @NotNull Page<Tag> findAll(@NotNull Pageable pageable);

    @Query("{'created_by': ?0}")
    @NotNull Page<Tag> findAllByCreatedBy(@NotNull String createdBy, @NotNull Pageable pageable);

    Tag deleteTagByTagId(String tagId);
}
