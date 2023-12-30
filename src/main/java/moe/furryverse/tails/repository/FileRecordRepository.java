package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.FileRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FileRecordRepository extends MongoRepository<FileRecord, String> {
    @NotNull <S extends FileRecord> S save(@NotNull S entity);

    @NotNull Page<FileRecord> findAll(@NotNull Pageable pageable);

    @Query("{'account_id': ?0}")
    @NotNull Page<FileRecord> findAllByAccountId(@NotNull String accountId, @NotNull Pageable pageable);
}
