package moe.furryverse.server.alnitak.repository;

import moe.furryverse.server.alnitak.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TagRepository extends MongoRepository<Tag, String> {
    <S extends Tag> @NotNull S save(@NotNull S tag);

    Tag findByTagId(String tagId);

    @Query(value = "{ 'tag_id' : ?0 }")
    Tag updateByTagId(String tagId, Tag tag);

    Tag deleteByTagId(String tagId);
}
