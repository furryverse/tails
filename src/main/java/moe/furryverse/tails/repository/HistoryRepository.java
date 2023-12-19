package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.History;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistoryRepository extends MongoRepository<History, String> {
    <S extends History> @NotNull S insert(@NotNull S history);

    History findByPostIdAndBindIdAndHistoryId(String postId, String bindId, String historyId);

    History deleteByPostIdAndBindIdAndHistoryId(String postId, String bindId, String historyId);

    List<History> findByPostIdAndBindId(String postId, String bindId);
}
