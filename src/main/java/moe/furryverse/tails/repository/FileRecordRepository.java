package moe.furryverse.tails.repository;

import moe.furryverse.tails.model.FileRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRecordRepository extends MongoRepository<FileRecord, String> {
    @NotNull <S extends FileRecord> S save(@NotNull S entity);

    FileRecord findFileRecordByFileId(String fileRecordId);

    FileRecord deleteFileRecordByFileId(String fileRecordId);
}
