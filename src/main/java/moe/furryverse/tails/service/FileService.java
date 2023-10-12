package moe.furryverse.tails.service;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.exception.FileFormatException;
import moe.furryverse.tails.exception.NotFoundDataException;
import moe.furryverse.tails.model.FileRecord;
import moe.furryverse.tails.repository.FileRecordRepository;
import moe.furryverse.tails.utils.Random;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {
    final FileRecordRepository fileRecordRepository;
    final MinioService minioService;

    public List<FileRecord> listFiles(String accountId) {
        return fileRecordRepository.findAllByAccountId(accountId);
    }

    public FileRecord getFile(String accountId, String fileId) {
        FileRecord fileRecord = fileRecordRepository.findByFileId(fileId);

        if (Objects.equals(fileRecord.accountId(), accountId) && !fileRecord.isPublic()) {
            throw new NotFoundDataException(
                    String.format("File %s not found", fileId),
                    String.format("/api/v0/file/%s", fileId),
                    "GET",
                    accountId
            );
        }

        return fileRecord;
    }

    public FileRecord uploadFile(String accountId, MultipartFile file) {
        if (
                file == null ||
                        file.isEmpty() ||
                        file.getOriginalFilename() == null ||
                        file.getContentType() == null
        ) {
            throw new FileFormatException(
                    "File is empty",
                    "/api/v0/upload",
                    "POST",
                    accountId
            );
        }

        String fileId = Random.uuid();

        // upload file to minio
        String path = minioService.uploadFile(fileId, file);

        FileRecord record = new FileRecord(
                fileId,
                accountId,
                System.currentTimeMillis(),
                file.getOriginalFilename(),
                path,
                file.getContentType(),
                file.getSize(),
                true
        );

        return fileRecordRepository.save(record);
    }

    public FileRecord deleteFile(String accountId, String fileId) {
        FileRecord fileRecord = fileRecordRepository.findByFileId(fileId);

        if (Objects.equals(fileRecord.accountId(), accountId) && !fileRecord.isPublic()) {
            throw new NotFoundDataException(
                    String.format("File %s not found", fileId),
                    String.format("/api/v0/file/%s", fileId),
                    "GET",
                    accountId
            );
        }

        return fileRecordRepository.deleteByFileIdAndAccountId(fileId, accountId);
    }
}
