package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.History;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<History, String> {
    @NotNull <S extends History> S save(@NotNull S entity);

    History findHistoryByHistoryId(String historyId);

    History deleteHistoryByHistoryId(String historyId);
}
