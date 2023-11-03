package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
}
