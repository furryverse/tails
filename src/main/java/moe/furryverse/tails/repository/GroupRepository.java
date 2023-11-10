package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Group;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GroupRepository extends MongoRepository<Group, String> {
    <S extends Group> @NotNull S save(@NotNull S group);

    Group findByGroupId(String groupId);

    @Query(value = "{ 'group_id' : ?0 }")
    Group updateByGroupId(String groupId, Group group);

    Group deleteByGroupId(String groupId);
}
