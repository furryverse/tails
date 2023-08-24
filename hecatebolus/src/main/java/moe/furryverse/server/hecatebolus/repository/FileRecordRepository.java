package moe.furryverse.server.hecatebolus.repository;

import moe.furryverse.server.hecatebolus.model.FileRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FileRecordRepository extends MongoRepository<FileRecord, String> {
    <S extends FileRecord> @NotNull S save(@NotNull S entity);

    @NotNull FileRecord findByFid(@NotNull String fid);

    void deleteByFidAndUid(@NotNull String fid, @NotNull String uid);

    @Query("{'fid': ?0, 'uid': ?1}")
    FileRecord updateByFid(@NotNull String fid, @NotNull FileRecord fileRecord);

    List<FileRecord> findAllByUid(@NotNull String uid);
}
