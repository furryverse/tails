package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.FileRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRecordRepository extends MongoRepository<FileRecord, String> {
    <S extends FileRecord> @NotNull S save(@NotNull S fileRecord);

    @NotNull FileRecord findByFileId(@NotNull String fileId);

    FileRecord deleteByFileIdAndAccountId(@NotNull String fileId, @NotNull String accountId);

    List<FileRecord> findAllByAccountId(@NotNull String accountId);
}
