package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
    @NotNull <S extends Tag> S save(@NotNull S entity);

    Tag findTagByTagId(String tagId);

    Tag deleteTagByTagId(String tagId);
}
