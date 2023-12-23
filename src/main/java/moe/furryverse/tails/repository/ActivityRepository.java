package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.Activity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    @NotNull <S extends Activity> S save(@NotNull S entity);

    Activity findActivityByActivityId(String activityId);

    Activity deleteActivityByActivityId(String activityId);
}
