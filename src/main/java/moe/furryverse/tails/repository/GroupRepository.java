package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Group;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
    <S extends Group> @NotNull S save(@NotNull S group);

    Group findByGroupId(String groupId);

    Group deleteByGroupId(String groupId);
}
