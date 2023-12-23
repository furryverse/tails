package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Group;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
    @NotNull <S extends Group> S save(@NotNull S entity);

    Group findGroupByGroupId(String groupId);

    Group deleteGroupByGroupId(String groupId);
}
