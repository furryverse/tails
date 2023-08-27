package moe.furryverse.server.hecatebolus.repository;

import moe.furryverse.server.hecatebolus.model.FileRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FileRecordRepository extends MongoRepository<FileRecord, String> {
    <S extends FileRecord> @NotNull S save(@NotNull S fileRecord);

    @NotNull FileRecord findByFileId(@NotNull String fileId);

    void deleteByFileIdAndAccountId(@NotNull String fileId, @NotNull String accountId);

    @Query("{'fid': ?0, 'uid': ?1}")
    FileRecord updateByFid(@NotNull String fileId, @NotNull FileRecord fileRecord);

    List<FileRecord> findAllByAccountId(@NotNull String accountId);
}
